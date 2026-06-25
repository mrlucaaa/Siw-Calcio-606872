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

import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.service.SquadraService;
import it.uniroma3.siw.exception.DuplicateSquadraException;

@Controller
public class SquadraController {

    private SquadraService squadraService;

    public SquadraController(SquadraService squadraService) {
        this.squadraService = squadraService;
    }

    @GetMapping("/squadre/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("squadra", this.squadraService.findById(id));
        return "squadre/show";
    }

    @GetMapping("/squadre")
    public String list(Model model) {
        List<Squadra> allSquadre = (List<Squadra>) this.squadraService.findAll();
        model.addAttribute("squadre", allSquadre);
        return "squadre/list";
    }
    
    @GetMapping("/squadre/new")
    public String formNewSquadra(Model model) {
        model.addAttribute("squadra", new Squadra());
        return "squadre/form";
    }

    @PostMapping("/squadre")
    public String newSquadra(@Valid @ModelAttribute("squadra") Squadra squadra, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "squadre/form";
        }
        try {
            this.squadraService.save(squadra);
            return "redirect:/squadre";
        } catch (DuplicateSquadraException e) {
            bindingResult.reject("squadra.duplicate");
            return "squadre/form";
        }
    }
    
    @GetMapping("/squadre/edit/{id}")
    public String editSquadra(@PathVariable("id") Long id, Model model) {
        model.addAttribute("squadra", this.squadraService.findById(id));
        return "squadre/edit";
    }
    
    @PostMapping("/squadre/edit/{id}")
    public String editSquadraPost(@Valid @ModelAttribute("squadra") Squadra squadra, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "squadre/edit";
        }
        try {
            this.squadraService.update(squadra);
            return "redirect:/squadre";
        } catch (DuplicateSquadraException e) {
            bindingResult.reject("squadra.duplicate");
            return "squadre/edit";
        }
    }
    
    @GetMapping("/squadre/delete/{id}")
    public String deleteSquadra(@PathVariable("id") Long id) {
        this.squadraService.deleteById(id);
        return "redirect:/squadre";
    }
}