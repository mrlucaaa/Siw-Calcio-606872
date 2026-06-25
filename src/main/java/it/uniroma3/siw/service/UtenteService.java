package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.UtenteRepository;

@Service
@Transactional(readOnly = true)
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
	
	public Utente findByUsername(String username) {
		return utenteRepository.findByUsername(username);
	}
	
	@Transactional
	public void save(Utente utente) {
		this.utenteRepository.save(utente);
	}
}
