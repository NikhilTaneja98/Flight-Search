package com.nagarro.Controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.dao.DAO;
import com.nagarro.watcher.Watcher;

@Controller
public class LoginController {

	@Autowired
	DAO database;

	@Autowired
	Watcher watch;

	public DAO getDatabase() {
		return database;
	}

	public void setDatabase(DAO database) {
		this.database = database;
	}

	@RequestMapping("login")
	public ModelAndView login(@RequestParam("user") String user, @RequestParam("pass") String pass) throws IOException {
		ModelAndView mv = new ModelAndView();
		database.getRecords();
		Thread t = new Thread(watch);
		t.start();
		if (database == null) {
			System.out.println("na");
		}
		if (database.validateUser(user, pass)) {
			mv.setViewName("home");
			// System.out.println("Hello");
			mv.addObject("name", user);
			return mv;
		} else {
			mv.setViewName("login");
			// System.out.println("Hello1");
			return mv;
		}
	}
}
