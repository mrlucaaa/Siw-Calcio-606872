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
import it.uniroma3.siw.controller.validator.SquadraValidator;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class SquadraController {

    private SquadraService squadraService;
    
    @Autowired
    private SquadraValidator squadraValidator;

    public SquadraController(SquadraService squadraService) {
        this.squadraService = squadraService;
    }

    @GetMapping("/squadre/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("squadra", this.squadraService.findById(id).orElse(null));
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
        this.squadraValidator.validate(squadra, bindingResult);
        if (bindingResult.hasErrors()) {
            return "squadre/form";
        }
        this.squadraService.save(squadra);
        return "redirect:/squadre";
    }
    
    @GetMapping("/squadre/edit/{id}")
    public String editSquadra(@PathVariable("id") Long id, Model model) {
        model.addAttribute("squadra", this.squadraService.findById(id).orElse(null));
        return "squadre/form";
    }
    
    @GetMapping("/squadre/delete/{id}")
    public String deleteSquadra(@PathVariable("id") Long id) {
        this.squadraService.deleteById(id);
        return "redirect:/squadre";
    }
}