package it.uniroma3.siw.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.repository.PartitaRepository;

@Service
@Transactional(readOnly = true)
public class PartitaService {
	private PartitaRepository partitaRepository;
	
	
	public PartitaService(PartitaRepository partitaRepository) {
			this.partitaRepository = partitaRepository;
	}
	
	public Partita findById(Long id){
		return partitaRepository.findById(id).orElseThrow(() -> new it.uniroma3.siw.exception.PartitaNotFoundException());
	}
	
	public Iterable<Partita> findAll(){
		return partitaRepository.findAll();
	}
	
	@Transactional
	public void save(Partita partita) {
        this.partitaRepository.save(partita);
    }
	
	@Transactional
	public void deleteById(Long id) {
        this.partitaRepository.deleteById(id);
    }
	
}
