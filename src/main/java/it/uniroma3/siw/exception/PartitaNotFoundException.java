package it.uniroma3.siw.exception;

public class PartitaNotFoundException extends RuntimeException {
    public PartitaNotFoundException() {
        super("Partita non trovata");
    }
}
