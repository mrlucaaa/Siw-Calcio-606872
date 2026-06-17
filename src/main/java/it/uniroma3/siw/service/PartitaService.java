package it.uniroma3.siw.service;

import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.repository.PartitaRepository;

@Service
public class PartitaService {
	private PartitaRepository partitaRepository;
	
	
	public PartitaService(PartitaRepository partitaRepository) {
			this.partitaRepository = partitaRepository;
	}
	
	public Partita findById(Long id){
		return partitaRepository.findById(id).orElse(null);
	}
	
	public Iterable<Partita> findAll(){
		return partitaRepository.findAll();
	}
	
	public void save(Partita partita) {
        this.partitaRepository.save(partita);
    }
	
	public void deleteById(Long id) {
        this.partitaRepository.deleteById(id);
    }
}
