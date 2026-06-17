package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Arbitro;
import it.uniroma3.siw.repository.ArbitroRepository;

@Service
public class ArbitroService {
	private ArbitroRepository arbitroRepository;
	
	
	public ArbitroService(ArbitroRepository arbitroRepository) {
			this.arbitroRepository = arbitroRepository;
	}
	
	public Optional<Arbitro> findById(Long id){
		return arbitroRepository.findById(id);
	}
	
	public Iterable<Arbitro> findAll(){
		return arbitroRepository.findAll();
	}
	
	public void save(Arbitro arbitro) {
		this.arbitroRepository.save(arbitro);
	}
}
