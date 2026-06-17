package it.uniroma3.siw.service;

import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.repository.CommentoRepository;

@Service
public class CommentoService {
	private CommentoRepository commentoRepository;
	
	
	public CommentoService(CommentoRepository commentoRepository) {
			this.commentoRepository = commentoRepository;
	}
	
	public Commento findById(Long id){
		return commentoRepository.findById(id).orElse(null);
	}
	
	public Iterable<Commento> findAll(){
		return commentoRepository.findAll();
	}
	
	public void save(Commento commento) {
        this.commentoRepository.save(commento);
    }
}
