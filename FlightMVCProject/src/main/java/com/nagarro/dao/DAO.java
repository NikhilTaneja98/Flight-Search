package com.nagarro.dao;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.nagarro.model.Flight;
import com.nagarro.model.User;

@Component
public class DAO {

	EntityManagerFactory factory;

	public void getRecords() throws IOException {
		ArrayList<Path> al = new ArrayList<>();
		Map<Path, List<Flight>> hm = new HashMap<>();
		URL url = ResourceUtils.getURL("classpath:Data");
		String path = url.getPath();
		path = path.substring(1);
		path = path.replaceAll("/", "//");
		System.out.println("Path " + path);
		try (Stream<Path> s = Files.walk(Paths.get(path))) {
			s.forEach(a -> {
				System.out.println("files ");
				System.out.println(a);
				System.out.println(a.getFileName());
				al.add(a);
			});
		} catch (Exception ex) {

		}
		al.remove(0); // Remove root
		CSVFormat ftm = CSVFormat.EXCEL.withDelimiter('|');
		List<Flight> list = new ArrayList<>();
		for (Path p : al) {
			try (Reader br = Files.newBufferedReader(p);
					CSVParser parse = new CSVParser(br, ftm.withFirstRecordAsHeader());) {
				for (CSVRecord r : parse) {
					Flight a = new Flight(r.get(0), r.get(1), r.get(2), r.get(3), r.get(4), r.get(5),
							Integer.parseInt(r.get(6)), r.get(7), r.get(8));
					list.add(a);

				}
				hm.put(p, list);
				list = new ArrayList<>();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		addToDatabase(hm);
	}

	public void addToDatabase(Map<Path, List<Flight>> hm) {
		factory = Persistence.createEntityManagerFactory("Flight-MVC");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		String query = "delete from Flight";
		Query q = em.createQuery(query);
		q.executeUpdate();
		try {
			for (Map.Entry<Path, List<Flight>> map : hm.entrySet()) {
				for (Flight f : map.getValue()) {
					em.persist(f);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
			System.err.println("Issue occured while adding persistent objects to the database.");
		}
		em.close();
	}

	public boolean validateUser(String user, String pass) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("User-MVC");
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("from User where username=\'" + user + "\'");
		List<User> al = q.getResultList();
		if (al.isEmpty()) {
			System.err.println("Username do not match");
			return false;
		}
		if (!al.get(0).getPassword().equals(pass)) {
			System.err.println("Invalid Password");
			return false;
		}
		return true;
	}

	public ArrayList<Flight> search(ArrayList<String> input) throws ParseException {
		Query query = factory.createEntityManager().createQuery(
				"from Flight where departure=\'" + input.get(0) + "\' AND arrival=\'" + input.get(1) + "\'");
		List<Flight> list = query.getResultList();
		ArrayList<Flight> al = new ArrayList<>();

		Flight f;
		for (int i = 0; i < list.size(); i++) {
			f = list.get(i);
			if (!compareDate(f.getValid(), input.get(2))) {
				continue;
			}
			if (!f.getFlightclass().contains(input.get(3).toUpperCase())) {
				continue;
			}
			al.add(f);
		}
		return al;
	}

	boolean compareDate(String a, String b) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		format.setLenient(false);
		try {
			Date d1 = format.parse(a);
			Date d2 = format.parse(b);
			if (d1.compareTo(d2) >= 0) {
				return true;
			}
		} catch (Exception ex) {
			System.err.println("Invalid Date Entered.");
			System.exit(0);
		}
		return false;
	}

	public ArrayList<Flight> sort(ArrayList<Flight> al, String preference) {
		if (preference.equalsIgnoreCase("Fare")) {
			Collections.sort(al, new Comparator<Flight>() {
				@Override
				public int compare(Flight a, Flight b) {
					return a.getFare() - b.getFare();
				}
			});
		} else if (preference.equalsIgnoreCase("ByBoth")) {
			Collections.sort(al, new Comparator<Flight>() {
				@Override
				public int compare(Flight a, Flight b) {
					int first = a.getFare() - b.getFare();
					if (first != 0) {
						return first;
					}
					return a.getDuration().compareTo(b.getDuration());
				}
			});
		}
		return al;
	}

}
