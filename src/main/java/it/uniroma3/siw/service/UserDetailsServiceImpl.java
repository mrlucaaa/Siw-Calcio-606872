package it.uniroma3.siw.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.UtenteRepository;
// questa classe serve ad insegnare a spring boot come leggere gli utenti nel database
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UtenteRepository utenteRepository;

    public UserDetailsServiceImpl(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Cerchiamo l'utente nel database tramite la repository
        Utente utente = this.utenteRepository.findByUsername(username);
        
        if (utente == null) {
            throw new UsernameNotFoundException("Utente non trovato con username: " + username);
        }

        // Impacchettiamo l'utente nel formato richiesto da Spring Security.
        // Nota: Aggiungiamo "ROLE_" davanti al ruolo perché Spring Security si aspetta questa convenzione sintattica.
        return User.builder()
                .username(utente.getUsername())
                .password(utente.getPassword())
                .roles(utente.getRuolo().toString()) // Restituirà "USER" o "ADMIN" basandosi sulla tua Enum
                .build();
    }
}