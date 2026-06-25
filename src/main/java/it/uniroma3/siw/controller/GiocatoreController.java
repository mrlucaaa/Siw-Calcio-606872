package it.uniroma3.siw.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Giocatore;
import it.uniroma3.siw.service.GiocatoreService;
import it.uniroma3.siw.service.SquadraService; 

@Controller
public class GiocatoreController {

    private GiocatoreService giocatoreService;
    private SquadraService squadraService; 

    public GiocatoreController(GiocatoreService giocatoreService, SquadraService squadraService) {
        this.giocatoreService = giocatoreService;
        this.squadraService = squadraService;
    }

    @GetMapping("/giocatori/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("giocatore", this.giocatoreService.findById(id));
        return "giocatori/show";
    }

    @GetMapping("/giocatori")
    public String list(Model model) {
        List<Giocatore> allGiocatori = (List<Giocatore>) this.giocatoreService.findAll();
        model.addAttribute("giocatori", allGiocatori);
        return "giocatori/list";
    }
    
    @GetMapping("/giocatori/new")
    public String formNewGiocatore(Model model) {
        model.addAttribute("giocatore", new Giocatore());
        model.addAttribute("squadre", this.squadraService.findAll()); 
        return "giocatori/form";
    }

    @PostMapping("/giocatori")
    public String newGiocatore(@ModelAttribute("giocatore") Giocatore giocatore) {
        this.giocatoreService.save(giocatore);
        return "redirect:/giocatori";
    }
    
    @GetMapping("/giocatori/edit/{id}")
    public String editGiocatore(@PathVariable("id") Long id, Model model) {
        model.addAttribute("giocatore", this.giocatoreService.findById(id));
        model.addAttribute("squadre", this.squadraService.findAll());
        return "giocatori/form";
    }
    
    @GetMapping("/giocatori/delete/{id}")
    public String deleteGiocatori(@PathVariable("id") Long id) {
    	this.giocatoreService.deleteById(id);
    	return "redirect:/giocatori";
    }
    
}