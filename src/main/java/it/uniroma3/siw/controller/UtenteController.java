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

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.UtenteService;

@Controller
public class UtenteController {

    private UtenteService utenteService;

    public UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @GetMapping("/utenti/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("utente", this.utenteService.findById(id));
        return "utenti/show";
    }

    @GetMapping("/utenti")
    public String list(Model model) {
        List<Utente> allUtenti = (List<Utente>) this.utenteService.findAll();
        model.addAttribute("utenti", allUtenti);
        return "utenti/list";
    }
    
    @GetMapping("/utenti/new")
    public String formNewUtente(Model model) {
        model.addAttribute("utente", new Utente());
        return "utenti/form";
    }

    @PostMapping("/utenti")
    public String newUtente(@Valid @ModelAttribute("utente") Utente utente, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "utenti/form";
        }
        this.utenteService.save(utente);
        return "redirect:/utenti";
    }
    
    @GetMapping("/utenti/edit/{id}")
    public String editUtente(@PathVariable("id") Long id, Model model) {
        model.addAttribute("utente", this.utenteService.findById(id));
        return "utenti/form";
    }
}