package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.model.Giocatore;
import it.uniroma3.siw.service.GiocatoreService;

@RestController
public class GiocatoreRestController {

    @Autowired
    private GiocatoreService giocatoreService;

    // React chiamerà questo indirizzo per farsi dare la lista dei giocatori
    @GetMapping("/api/giocatori")
    public Iterable<Giocatore> getGiocatoriApi() {
        return this.giocatoreService.findAll();
    }
}