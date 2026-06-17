package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.model.RuoloUtente;
import it.uniroma3.siw.service.UtenteService;

@Controller
public class AuthController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("utente", new Utente());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("utente") Utente utente) {
        // Cifra la password prima di salvarla
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        
        // Imposta il ruolo di base
        utente.setRuolo(RuoloUtente.USER);
        
        // Salva l'utente
        utenteService.save(utente);
        
        // Reindirizza al login dopo il successo
        return "redirect:/login?registered=true";
    }
}
