package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.service.SquadraService;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String getHome() {
		return "index.html";
	}
	
}
