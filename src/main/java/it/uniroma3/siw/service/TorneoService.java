package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.model.RigaClassifica;
import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.model.StatoPartita;
import it.uniroma3.siw.model.Torneo;
import it.uniroma3.siw.repository.TorneoRepository;

@Service
public class TorneoService {
    private TorneoRepository torneoRepository;
    
    public TorneoService(TorneoRepository torneoRepository) {
            this.torneoRepository = torneoRepository;
    }
    
    public Torneo findById(Long id) {
        return this.torneoRepository.findById(id).orElse(null);
    }
    
    public Iterable<Torneo> findAll(){
        return torneoRepository.findAll();
    }
    
    @Transactional
    public void save(Torneo torneo) {
        this.torneoRepository.save(torneo);
    }
    
    @Transactional
    public List<RigaClassifica> calcolaClassifica(Long idTorneo) {
        Torneo torneo = this.torneoRepository.findById(idTorneo).orElse(null);
        if (torneo == null) return new ArrayList<>();

        Map<Long, RigaClassifica> mappa = new HashMap<>();

        for (Partita p : torneo.getPartite()) {
            if (p.getSquadraCasa() != null) {
                mappa.putIfAbsent(p.getSquadraCasa().getId(), new RigaClassifica(p.getSquadraCasa()));
            }
            if (p.getSquadraTrasferta() != null) {
                mappa.putIfAbsent(p.getSquadraTrasferta().getId(), new RigaClassifica(p.getSquadraTrasferta()));
            }
        }
        
        for (Squadra s : torneo.getSquadre()) {
            mappa.putIfAbsent(s.getId(), new RigaClassifica(s));
        }

        for (Partita p : torneo.getPartite()) {
            if (p.getStato() == StatoPartita.GIOCATA && p.getSquadraCasa() != null && p.getSquadraTrasferta() != null) {
                
                RigaClassifica rigaCasa = mappa.get(p.getSquadraCasa().getId());
                RigaClassifica rigaTrasferta = mappa.get(p.getSquadraTrasferta().getId());

                rigaCasa.setPartiteGiocate(rigaCasa.getPartiteGiocate() + 1);
                rigaTrasferta.setPartiteGiocate(rigaTrasferta.getPartiteGiocate() + 1);
                
                rigaCasa.setRetiFatte(rigaCasa.getRetiFatte() + p.getGoalsHome().intValue());
                rigaCasa.setRetiSubite(rigaCasa.getRetiSubite() + p.getGoalsAway().intValue());

                rigaTrasferta.setRetiFatte(rigaTrasferta.getRetiFatte() + p.getGoalsAway().intValue());
                rigaTrasferta.setRetiSubite(rigaTrasferta.getRetiSubite() + p.getGoalsHome().intValue());

                if (p.getGoalsHome() > p.getGoalsAway()) {
                    rigaCasa.setPunti(rigaCasa.getPunti() + 3);
                } else if (p.getGoalsHome() < p.getGoalsAway()) {
                    rigaTrasferta.setPunti(rigaTrasferta.getPunti() + 3);
                } else {
                    rigaCasa.setPunti(rigaCasa.getPunti() + 1);
                    rigaTrasferta.setPunti(rigaTrasferta.getPunti() + 1);
                }
            }
        }

        List<RigaClassifica> classificaFinale = new ArrayList<>(mappa.values());
        Collections.sort(classificaFinale); 
        
        return classificaFinale;
    }

    // --- NUOVO METODO: ANALISI SPERIMENTALE N+1 ---
    @Transactional
    public void eseguiAnalisiSperimentale() {
        System.out.println("============= INIZIO ANALISI SPERIMENTALE N+1 =============");
        
        // 1. TEST STRATEGIA LAZY (Comportamento di Default)
        long startLazy = System.currentTimeMillis();
        Iterable<Torneo> torneiLazy = this.torneoRepository.findAll(); // 1 Query
        for (Torneo t : torneiLazy) {
            int numeroSquadre = t.getSquadre().size(); // N Query! (Una per ogni torneo)
        }
        long endLazy = System.currentTimeMillis();
        long tempoLazy = endLazy - startLazy;
        System.out.println("Tempo impiegato con strategia LAZY: " + tempoLazy + " ms");

        System.out.println("-----------------------------------------------------------");

        // 2. TEST STRATEGIA EAGER CON JOIN FETCH (Ottimizzato)
        long startEager = System.currentTimeMillis();
        List<Torneo> torneiEager = this.torneoRepository.findAllWithSquadreEager(); // 1 Singola Query
        for (Torneo t : torneiEager) {
            int numeroSquadre = t.getSquadre().size(); // Nessuna query extra
        }
        long endEager = System.currentTimeMillis();
        long tempoEager = endEager - startEager;
        System.out.println("Tempo impiegato con strategia JOIN FETCH: " + tempoEager + " ms");
        
        System.out.println("===========================================================");
    }
}