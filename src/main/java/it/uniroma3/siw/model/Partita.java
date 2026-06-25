package it.uniroma3.siw.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Partita {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDateTime dataOra;
	@NotBlank
	private String luogo;
	@ManyToOne
	@NotNull
	private Torneo torneo;
	@ManyToOne
	@NotNull
	private Arbitro arbitro;
	@ManyToOne
	@NotNull
	private Squadra squadraCasa;
	@ManyToOne
	@NotNull
	private Squadra squadraTrasferta;
	private Long goalsHome;
	private Long goalsAway;
	@jakarta.persistence.Enumerated(jakarta.persistence.EnumType.STRING)
	private StatoPartita stato;
	@OneToMany(mappedBy = "partita", cascade = CascadeType.ALL)
    private List<Commento> commenti;
	
	
	public LocalDateTime getDataOra() {
		return dataOra;
	}
	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
	}
	public String getLuogo() {
		return luogo;
	}
	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}
	public Long getGoalsHome() {
		return goalsHome;
	}
	public void setGoalsHome(Long goalsHome) {
		this.goalsHome = goalsHome;
	}
	public Long getGoalsAway() {
		return goalsAway;
	}
	public void setGoalsAway(Long goalsAway) {
		this.goalsAway = goalsAway;
	}
	public StatoPartita getStato() {
		return stato;
	}
	public void setStato(StatoPartita stato) {
		this.stato = stato;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Torneo getTorneo() {
		return torneo;
	}
	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}
	public Arbitro getArbitro() {
		return arbitro;
	}
	public void setArbitro(Arbitro arbitro) {
		this.arbitro = arbitro;
	}
	public Squadra getSquadraCasa() {
		return squadraCasa;
	}
	public void setSquadraCasa(Squadra squadraCasa) {
		this.squadraCasa = squadraCasa;
	}
	public Squadra getSquadraTrasferta() {
		return squadraTrasferta;
	}
	public void setSquadraTrasferta(Squadra squadraTrasferta) {
		this.squadraTrasferta = squadraTrasferta;
	}
	public List<Commento> getCommenti() {
		return commenti;
	}
	public void setCommenti(List<Commento> commenti) {
		this.commenti = commenti;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dataOra, luogo, squadraCasa, squadraTrasferta);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partita other = (Partita) obj;
		return Objects.equals(dataOra, other.dataOra) && Objects.equals(luogo, other.luogo)
				&& Objects.equals(squadraCasa, other.squadraCasa)
				&& Objects.equals(squadraTrasferta, other.squadraTrasferta);
	}
}
