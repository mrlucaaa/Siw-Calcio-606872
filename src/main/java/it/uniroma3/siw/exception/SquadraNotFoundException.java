package it.uniroma3.siw.exception;

public class SquadraNotFoundException extends RuntimeException {
    public SquadraNotFoundException() {
        super("Squadra non trovata");
    }
}
