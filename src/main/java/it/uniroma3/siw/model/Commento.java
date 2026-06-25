package it.uniroma3.siw.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Commento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String testo;
	private LocalDateTime dataOra;
	@ManyToOne
	@NotNull
	private Utente autore;
	@ManyToOne
	@NotNull
	private Partita partitaRiferimento;
	@ManyToOne
	@NotNull
    private Partita partita;
	
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public Utente getAutore() {
		return autore;
	}
	public void setAutore(Utente autore) {
		this.autore = autore;
	}
	public LocalDateTime getDataOra() {
		return dataOra;
	}
	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
	}
	public Partita getPartitaRiferimento() {
		return partitaRiferimento;
	}
	public void setPartitaRiferimento(Partita partitaRiferimento) {
		this.partitaRiferimento = partitaRiferimento;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Partita getPartita() {
		return partita;
	}
	public void setPartita(Partita partita) {
		this.partita = partita;
	}
}
