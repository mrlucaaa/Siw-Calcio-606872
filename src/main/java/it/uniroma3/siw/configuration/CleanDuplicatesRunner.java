package it.uniroma3.siw.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CleanDuplicatesRunner implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        // Rimuove eventuali utenti duplicati tenendo solo quello con l'ID minore
        jdbcTemplate.execute("DELETE FROM utente WHERE id NOT IN (SELECT MIN(id) FROM utente GROUP BY username)");
    }
}
