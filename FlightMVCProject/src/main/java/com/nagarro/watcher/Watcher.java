package com.nagarro.watcher;

import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.nagarro.dao.DAO;

@Component
public class Watcher implements Runnable {

	@Autowired
	DAO database;

	@Override
	public void run() {
		try (WatchService service = FileSystems.getDefault().newWatchService()) {
			Map<WatchKey, Path> hm = new HashMap<>();
			URL url = ResourceUtils.getURL("classpath:Data");
			String path = url.getPath();
			path = path.substring(1);
			path = path.replaceAll("/", "//");
			System.out.println("Path " + path);
			Path file = Paths.get(path);
			hm.put(file.register(service, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY), file);
			WatchKey key;
			do {
				String check = "";
				key = service.take();
				for (WatchEvent<?> event : key.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();
					Path eventPath = (Path) event.context();
					check += eventPath;

				}
				if (check.length() > 0) {
					database.getRecords();
					// System.out.println(App.dataMap.toString());
				}
				Thread.sleep(3000);

			} while (key.reset());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
