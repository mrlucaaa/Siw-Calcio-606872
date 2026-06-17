package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.UtenteRepository;

@Service
public class UtenteService {
	private UtenteRepository utenteRepository;
	
	
	public UtenteService(UtenteRepository utenteRepository) {
			this.utenteRepository = utenteRepository;
	}
	
	public Optional<Utente> findById(Long id){
		return utenteRepository.findById(id);
	}
	
	public Iterable<Utente> findAll(){
		return utenteRepository.findAll();
	}
	
	public void save(Utente utente) {
		this.utenteRepository.save(utente);
	}
}
