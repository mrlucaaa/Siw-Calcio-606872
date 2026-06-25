package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.repository.SquadraRepository;

@Service
@Transactional(readOnly = true)
public class SquadraService {
	private SquadraRepository squadraRepository;
	
	
	public SquadraService(SquadraRepository squadraRepository) {
			this.squadraRepository = squadraRepository;
	}
	
	public Squadra findById(Long id){
		return squadraRepository.findById(id).orElseThrow(() -> new it.uniroma3.siw.exception.SquadraNotFoundException());
	}
	
	public Iterable<Squadra> findAll(){
		return squadraRepository.findAll();
	}
	
	public boolean existsByNome(String nome) {
		return squadraRepository.existsByNome(nome);
	}
	
	@Transactional
	public void save(Squadra squadra) throws it.uniroma3.siw.exception.DuplicateSquadraException {
		if (this.squadraRepository.existsByNome(squadra.getNome())) {
			throw new it.uniroma3.siw.exception.DuplicateSquadraException();
		}
        this.squadraRepository.save(squadra);
    }
	
	@Transactional
	public void update(Squadra squadraNuova) throws it.uniroma3.siw.exception.DuplicateSquadraException {
		Squadra squadraVecchia = this.findById(squadraNuova.getId());
		
		if (!squadraVecchia.getNome().equals(squadraNuova.getNome())) {
			if (this.squadraRepository.existsByNome(squadraNuova.getNome())) {
				throw new it.uniroma3.siw.exception.DuplicateSquadraException();
			}
		}
		
		squadraVecchia.setNome(squadraNuova.getNome());
		squadraVecchia.setCittà(squadraNuova.getCittà());
		squadraVecchia.setAnnoDiFondazione(squadraNuova.getAnnoDiFondazione());
		this.squadraRepository.save(squadraVecchia);
	}
	
    @Transactional
    public void deleteById(Long id) {
        this.squadraRepository.deleteById(id);
    }
   
}
