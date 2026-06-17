package it.uniroma3.siw.model;

// Implementa Comparable per poterla ordinare automaticamente in base ai punti
public class RigaClassifica implements Comparable<RigaClassifica> {
    
    private Squadra squadra;
    private int punti;
    private int partiteGiocate;
    private int retiFatte;
    private int retiSubite;

    public RigaClassifica(Squadra squadra) {
        this.squadra = squadra;
        this.punti = 0;
        this.partiteGiocate = 0;
        this.retiFatte = 0;
        this.retiSubite = 0;
    }

    public Squadra getSquadra() { return squadra; }
    public int getPunti() { return punti; }
    public int getPartiteGiocate() { return partiteGiocate; }
    public int getRetiFatte() { return retiFatte; }
    public int getRetiSubite() { return retiSubite; }
    public int getDifferenzaReti() { return retiFatte - retiSubite; }

    
    public void setPunti(int punti) { this.punti = punti; }
    public void setPartiteGiocate(int partiteGiocate) { this.partiteGiocate = partiteGiocate; }
    public void setRetiFatte(int retiFatte) { this.retiFatte = retiFatte; }
    public void setRetiSubite(int retiSubite) { this.retiSubite = retiSubite; }

    // Questo metodo insegna a Java come ordinare la classifica (dal più alto al più basso)
    // se il risultato è positivo this va dopo in classifica altrimenti va prima
    @Override
    public int compareTo(RigaClassifica altra) {
        if (this.punti != altra.punti) {
            return Integer.compare(altra.punti, this.punti); // Ordina per punti decrescenti
        }
        // Se hanno gli stessi punti, ordina per differenza reti
        return Integer.compare(altra.getDifferenzaReti(), this.getDifferenzaReti());
    }
}