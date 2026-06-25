package it.uniroma3.siw.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import it.uniroma3.siw.exception.GiocatoreNotFoundException;
import it.uniroma3.siw.exception.PartitaNotFoundException;
import it.uniroma3.siw.exception.SquadraNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
        PartitaNotFoundException.class,
        SquadraNotFoundException.class,
        GiocatoreNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundExceptions(Exception ex, Model model) {
        String messageKey = null;
        if (ex instanceof PartitaNotFoundException) messageKey = "partita.notFound";
        else if (ex instanceof SquadraNotFoundException) messageKey = "squadra.notFound";
        else if (ex instanceof GiocatoreNotFoundException) messageKey = "giocatore.notFound";
        
        if (messageKey != null) {
            model.addAttribute("messaggioErrore", messageKey);
        }
        return "error/404.html";
    }
    
    // Generic 404 handler for wrong URLs
    @ExceptionHandler({NoHandlerFoundException.class, NoResourceFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoHandlerFoundException(Exception ex, Model model) {
        return "error/404.html";
    }

    // Generic 500 handler for any other unhandled exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGlobalException(Exception ex, Model model) {
        // Puoi fare il logging dell'eccezione qui
        return "error/500.html";
    }
}
