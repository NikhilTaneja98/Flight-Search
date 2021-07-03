package com.nagarro.Controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.dao.DAO;
import com.nagarro.model.Flight;
import com.nagarro.watcher.Watcher;

@Controller
public class HomeController {

	@Autowired
	DAO database;

	@Autowired
	Watcher watch;

	@RequestMapping("home")
	public ModelAndView mainhome(@RequestParam("dep") String dep, @RequestParam("arr") String arr,
			@RequestParam("date") String date, @RequestParam("class") String clas, @RequestParam("pref") String pref)
			throws ParseException, IOException {
		ModelAndView mv = new ModelAndView();
		ArrayList<String> al = new ArrayList<>();
		al.add(dep);
		al.add(arr);
		al.add(date);
		al.add(clas);
		al.add(pref);
		ArrayList<Flight> list = database.search(al);
		list = database.sort(list, pref);
		mv.addObject("data", list);
		mv.setViewName("home");
		mv.addObject("type", clas);
		return mv;
	}
}
