package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Giocatore;
import it.uniroma3.siw.repository.GiocatoreRepository;

@Service
public class GiocatoreService {
	private GiocatoreRepository giocatoreRepository;
	
	
	public GiocatoreService(GiocatoreRepository giocatoreRepository) {
			this.giocatoreRepository = giocatoreRepository;
	}
	
	public Optional<Giocatore> findById(Long id){
		return giocatoreRepository.findById(id);
	}
	
	public Iterable<Giocatore> findAll(){
		return giocatoreRepository.findAll();
	}
	
	public void save(Giocatore giocatore) {
	    this.giocatoreRepository.save(giocatore);
	}
	
	public void deleteById(Long id) {
		this.giocatoreRepository.deleteById(id);
	}
	
}
