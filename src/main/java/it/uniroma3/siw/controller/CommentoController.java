package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.repository.CommentoRepository;
import it.uniroma3.siw.repository.PartitaRepository;
import it.uniroma3.siw.repository.UtenteRepository;
import it.uniroma3.siw.service.CommentoService;
import it.uniroma3.siw.service.PartitaService;
import it.uniroma3.siw.service.UtenteService;

@Controller
public class CommentoController {

    @Autowired
    private CommentoService commentoService;
    
    @Autowired
    private UtenteService utenteService;
    
    @Autowired
    private PartitaService partitaService;

    @Autowired
    private PartitaRepository partitaRepository;

    @Autowired
    private CommentoRepository commentoRepository;

    @Autowired
    private UtenteRepository utenteRepository; 



    @GetMapping("/commenti")
    public String list(Model model) {
        List<Commento> allCommenti = (List<Commento>) this.commentoService.findAll();
        model.addAttribute("commenti", allCommenti);
        return "commenti/list";
    }
    
    

    @PostMapping("/commenti/new")
    public String salvaCommento(@RequestParam("partita_id") Long partitaId, 
                                @RequestParam("testo") String testo) {
        
        Partita partita = this.partitaRepository.findById(partitaId).orElse(null);
        if (partita == null) return "redirect:/";
        
        Commento commento = new Commento();
        commento.setTesto(testo);
        commento.setPartita(partita);

        //vedo chi sta navigando il sito
        Object principal = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        //vedo se chi sta navigando è registrato
        if (principal instanceof org.springframework.security.core.userdetails.UserDetails) {
        	//se è registrato prendo l'username
            String username = ((org.springframework.security.core.userdetails.UserDetails) principal).getUsername();
            it.uniroma3.siw.model.Utente autore = this.utenteRepository.findByUsername(username);
            commento.setAutore(autore);
        }

        this.commentoRepository.save(commento);
        
        return "redirect:/partite/" + partitaId;
    }

    @GetMapping("/commenti/delete/{id}")
    public String deleteCommento(@PathVariable("id") Long id) {
        Commento commento = this.commentoRepository.findById(id).orElse(null);
        
        if (commento != null) {
            Long partitaId = commento.getPartita().getId();
            
            // Recuperiamo l'utente loggato attualmente
            Object principal = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String currentUsername = "";
            if (principal instanceof org.springframework.security.core.userdetails.UserDetails) {
                currentUsername = ((org.springframework.security.core.userdetails.UserDetails) principal).getUsername();
            }
            
            // CONTROLLO DI SICUREZZA: Cancella solo se sei l'admin OPPURE se sei l'autore del commento
            boolean isAdmin = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                    .stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
            
            if (isAdmin || currentUsername.equals(commento.getAutore().getUsername())) {
                this.commentoRepository.delete(commento);
            } else {
                return "redirect:/login?error=unauthorized";
            }
            
            return "redirect:/partite/" + partitaId;
        }
        return "redirect:/";
    }
    
 // 1. APRE LA PAGINA DI MODIFICA
    @GetMapping("/commenti/edit/{id}")
    public String editCommentoForm(@PathVariable("id") Long id, Model model) {
        Commento commento = this.commentoRepository.findById(id).orElse(null);
        if (commento == null) return "redirect:/";

        // Controllo Sicurezza: sei l'autore o sei l'admin?
        Object principal = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUsername = "";
        if (principal instanceof org.springframework.security.core.userdetails.UserDetails) {
            currentUsername = ((org.springframework.security.core.userdetails.UserDetails) principal).getUsername();
        }
        boolean isAdmin = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin || currentUsername.equals(commento.getAutore().getUsername())) {
            model.addAttribute("commento", commento);
            return "commenti/edit"; // Rimanda al file HTML che creeremo ora
        } else {
            return "redirect:/login?error=unauthorized";
        }
    }

    // 2. SALVA LA MODIFICA E TORNA ALLA PARTITA
    @PostMapping("/commenti/edit/{id}")
    public String updateCommento(@PathVariable("id") Long id, @RequestParam("testo") String testo) {
        Commento commento = this.commentoRepository.findById(id).orElse(null);
        if (commento != null) {
            Long partitaId = commento.getPartita().getId();
            
            // Aggiorna il testo e salva
            commento.setTesto(testo);
            this.commentoRepository.save(commento);
            
            return "redirect:/partite/" + partitaId;
        }
        return "redirect:/";
    }
    
    
}