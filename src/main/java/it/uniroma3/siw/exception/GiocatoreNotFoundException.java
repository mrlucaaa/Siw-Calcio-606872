package it.uniroma3.siw.exception;

public class GiocatoreNotFoundException extends RuntimeException {
    public GiocatoreNotFoundException() {
        super("Giocatore non trovato");
    }
}
