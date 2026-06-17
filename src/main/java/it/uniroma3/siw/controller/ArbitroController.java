package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Arbitro;
import it.uniroma3.siw.service.ArbitroService;

@Controller
public class ArbitroController {

    private ArbitroService arbitroService;

    public ArbitroController(ArbitroService arbitroService) {
        this.arbitroService = arbitroService;
    }

    @GetMapping("/arbitri/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("arbitro", this.arbitroService.findById(id).orElse(null));
        return "arbitri/show";
    }

    @GetMapping("/arbitri")
    public String list(Model model) {
        List<Arbitro> allArbitri = (List<Arbitro>) this.arbitroService.findAll();
        model.addAttribute("arbitri", allArbitri);
        return "arbitri/list";
    }
    
    @GetMapping("/arbitri/new")
    public String formNewArbitro(Model model) {
        model.addAttribute("arbitro", new Arbitro());
        return "arbitri/form";
    }

    @PostMapping("/arbitri")
    public String newArbitro(@ModelAttribute("arbitro") Arbitro arbitro) {
        this.arbitroService.save(arbitro);
        return "redirect:/arbitri";
    }
}