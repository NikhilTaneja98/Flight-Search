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

import org.springframework.util.ResourceUtils;

public class app {

	public static void main(String[] args) {
		try (WatchService service = FileSystems.getDefault().newWatchService()) {
			Map<WatchKey, Path> hm = new HashMap<>();
			ResourceUtils.getFile("classpath:Data");
			URL url = ResourceUtils.getURL("classpath:Data");
			System.out.println("url " + url.getPath());
			Path file = Paths.get("//Data");
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
					// database.getRecords();
					// System.out.println(App.dataMap.toString());
				}
				Thread.sleep(3000);

			} while (key.reset());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}