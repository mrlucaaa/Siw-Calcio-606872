package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Torneo;

public interface TorneoRepository extends CrudRepository<Torneo, Long>{

    // Metodo ottimizzato per risolvere il problema N+1
    @Query("SELECT DISTINCT t FROM Torneo t LEFT JOIN FETCH t.squadre")
    List<Torneo> findAllWithSquadreEager();

}