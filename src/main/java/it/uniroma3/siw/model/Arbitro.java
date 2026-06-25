package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Arbitro {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long codiceArbitrale;
	@NotBlank
	private String nome;
	@NotBlank
	private String cognome;
	@OneToMany(mappedBy = "arbitro")
	private List<Partita> partiteDirette;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Long getCodiceArbitrale() {
		return codiceArbitrale;
	}
	public void setCodiceArbitrale(Long codiceArbitrale) {
		this.codiceArbitrale = codiceArbitrale;
	}
	public List<Partita> getPartiteDirette() {
		return partiteDirette;
	}
	public void setPartiteDirette(List<Partita> partiteDirette) {
		this.partiteDirette = partiteDirette;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codiceArbitrale);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arbitro other = (Arbitro) obj;
		return Objects.equals(codiceArbitrale, other.codiceArbitrale);
	}
}
