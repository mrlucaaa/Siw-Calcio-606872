package it.uniroma3.siw.controller;

import it.uniroma3.siw.service.PartitaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	
	@GetMapping("/")
	public String getHome() {
		return "index.html";
	}
}
