package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.service.PartitaService;
import it.uniroma3.siw.service.SquadraService;
import it.uniroma3.siw.service.TorneoService; // AGGIUNTA IMPORTAZIONE

@Controller
public class PartitaController {

    @Autowired
    private PartitaService partitaService;
    
    @Autowired
    private SquadraService squadraService;
    
    @Autowired
    private TorneoService torneoService;

    @GetMapping("/partite/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("partita", this.partitaService.findById(id));
        return "partite/show";
    }

    @GetMapping("/partite")
    public String list(Model model) {
        List<Partita> allPartite = (List<Partita>) this.partitaService.findAll();
        model.addAttribute("partite", allPartite);
        return "partite/list";
    }
    
    @GetMapping("/partite/new")
    public String formNewPartita(Model model) {
        model.addAttribute("partita", new Partita());
        
        model.addAttribute("squadre", this.squadraService.findAll()); 
        model.addAttribute("tornei", this.torneoService.findAll());
        
        return "partite/form";
    }

    @PostMapping("/partite")
    public String newPartita(@Valid @ModelAttribute("partita") Partita partita, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("squadre", this.squadraService.findAll()); 
            model.addAttribute("tornei", this.torneoService.findAll());
            return "partite/form";
        }
        this.partitaService.save(partita);
        return "redirect:/partite";
    }
    
    @GetMapping("/partite/edit/{id}")
    public String editPartita(@PathVariable("id") Long id, Model model) {
        model.addAttribute("partita", this.partitaService.findById(id));
        model.addAttribute("squadre", this.squadraService.findAll()); 
        model.addAttribute("tornei", this.torneoService.findAll());
        return "partite/form";
    }

    @GetMapping("/partite/delete/{id}")
    public String deletePartita(@PathVariable("id") Long id) {
        this.partitaService.deleteById(id);
        return "redirect:/partite";
    }
}