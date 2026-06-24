package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import it.uniroma3.siw.model.Torneo;
import it.uniroma3.siw.service.TorneoService;

@Controller
public class TorneoController {

    private TorneoService torneoService;

    public TorneoController(TorneoService torneoService) {
        this.torneoService = torneoService;
    }

    @GetMapping("/tornei/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("torneo", this.torneoService.findById(id));
        model.addAttribute("classifica", this.torneoService.calcolaClassifica(id));
        return "tornei/show";
    }

    @GetMapping("/tornei")
    public String list(Model model) {
        // ESEGUO L'ANALISI SPERIMENTALE PRIMA DI CARICARE LA PAGINA
        this.torneoService.eseguiAnalisiSperimentale();
        
        List<Torneo> allTornei = (List<Torneo>) this.torneoService.findAll();
        model.addAttribute("tornei", allTornei);
        return "tornei/list";
    }
    
    @GetMapping("/tornei/new")
    public String formNewTorneo(Model model) {
        model.addAttribute("torneo", new Torneo());
        return "tornei/form";
    }

    @PostMapping("/tornei")
    public String newTorneo(@Valid @ModelAttribute("torneo") Torneo torneo, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "tornei/form";
        }
        this.torneoService.save(torneo);
        return "redirect:/tornei";
    }
    
    @GetMapping("/tornei/edit/{id}")
    public String editTorneo(@PathVariable("id") Long id, Model model) {
        model.addAttribute("torneo", this.torneoService.findById(id));
        return "tornei/form";
    }
}