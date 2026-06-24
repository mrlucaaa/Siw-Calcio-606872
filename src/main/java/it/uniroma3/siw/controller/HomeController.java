package it.uniroma3.siw.controller;

import it.uniroma3.siw.service.PartitaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	private final PartitaService partitaService;

	public HomeController(PartitaService partitaService) {
		this.partitaService = partitaService;
	}
	
	@GetMapping("/")
	public String getHome(Model model) {
		Long numeroPartite=this.partitaService.count();
		model.addAttribute("totalePartite", numeroPartite);
		return "index.html";
	}
}
