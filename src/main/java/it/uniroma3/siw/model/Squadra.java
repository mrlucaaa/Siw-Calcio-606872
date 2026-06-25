package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Squadra {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String nome;
	private int annoDiFondazione;
	private String città;
	@ManyToMany
	@com.fasterxml.jackson.annotation.JsonIgnore
	private List<Torneo> tornei;
	@OneToMany(mappedBy = "squadra", cascade = CascadeType.ALL)
	@com.fasterxml.jackson.annotation.JsonIgnore
    private List<Giocatore> giocatori;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getAnnoDiFondazione() {
		return annoDiFondazione;
	}
	public void setAnnoDiFondazione(int annoDiFondazione) {
		this.annoDiFondazione = annoDiFondazione;
	}
	public String getCittà() {
		return città;
	}
	public void setCittà(String città) {
		this.città = città;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Torneo> getTornei() {
		return tornei;
	}
	public void setTornei(List<Torneo> tornei) {
		this.tornei = tornei;
	}
	public List<Giocatore> getGiocatori() {
		return giocatori;
	}
	public void setGiocatori(List<Giocatore> giocatori) {
		this.giocatori = giocatori;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Squadra other = (Squadra) obj;
		return Objects.equals(nome, other.nome);
	}
}
