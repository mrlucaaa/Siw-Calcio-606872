package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.repository.SquadraRepository;

@Service
public class SquadraService {
	private SquadraRepository squadraRepository;
	
	
	public SquadraService(SquadraRepository squadraRepository) {
			this.squadraRepository = squadraRepository;
	}
	
	public Optional<Squadra> findById(Long id){
		return squadraRepository.findById(id);
	}
	
	public Iterable<Squadra> findAll(){
		return squadraRepository.findAll();
	}
	
	public void save(Squadra squadra) {
        this.squadraRepository.save(squadra);
    }
	
    public void deleteById(Long id) {
        this.squadraRepository.deleteById(id);
    }
}
