package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.service.SquadraService;

@Component
public class SquadraValidator implements Validator {

    @Autowired
    private SquadraService squadraService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Squadra.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Squadra squadra = (Squadra) target;
        if (squadra.getNome() != null && squadraService.existsByNome(squadra.getNome())) {
            errors.rejectValue("nome", "duplicate", "Esiste già una squadra con questo nome");
        }
    }
}
