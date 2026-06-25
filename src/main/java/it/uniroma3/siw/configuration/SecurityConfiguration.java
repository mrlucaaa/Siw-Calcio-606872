package it.uniroma3.siw.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // 1. File statici e pagina di ERRORE accessibili a chiunque
                .requestMatchers("/css/**", "/js/**", "/react/**", "/images/**", "/favicon.ico", "/error").permitAll()

                // 2. ROTTE ADMIN (Creazione, modifica ed eliminazione riservate all'amministratore)
                .requestMatchers("/tornei/new", "/tornei/edit/**", "/tornei/delete/**").hasRole("ADMIN")
                .requestMatchers("/squadre/new", "/squadre/edit/**", "/squadre/delete/**").hasRole("ADMIN")
                .requestMatchers("/giocatori/new", "/giocatori/edit/**", "/giocatori/delete/**").hasRole("ADMIN")
                .requestMatchers("/partite/new", "/partite/edit/**", "/partite/delete/**").hasRole("ADMIN")
                .requestMatchers("/arbitri/new", "/arbitri/edit/**", "/arbitri/delete/**").hasRole("ADMIN")
                .requestMatchers("/utenti/**").hasRole("ADMIN") 
                
                // Salvataggi dei dati (metodi POST) bloccati per i non-admin
                .requestMatchers(HttpMethod.POST, "/tornei", "/squadre", "/giocatori", "/partite", "/arbitri").hasRole("ADMIN")
                
                // 3. Utenti Autenticati (USER o ADMIN)
                // L'invio di un commento (POST) richiede il login
                .requestMatchers(HttpMethod.POST, "/commenti/**").authenticated()
                
                // 4. ROTTE PUBBLICHE (Consultazione libera per tutti)
                // Usiamo HttpMethod.GET e il doppio asterisco /** per coprire tutti i dettagli!
                .requestMatchers(HttpMethod.GET, 
                    "/", "/index", 
                    "/tornei", "/tornei/**", 
                    "/squadre", "/squadre/**", 
                    "/giocatori", "/giocatori/**", 
                    "/partite", "/partite/**", 
                    "/arbitri", "/arbitri/**", 
                    "/commenti", "/commenti/**",
                    "/api/**",
                    "/login", "/register"
                ).permitAll()
                
                // Consenti la registrazione
                .requestMatchers(HttpMethod.POST, "/register").permitAll()
                
                // Qualsiasi altra richiesta residua richiede autenticazione
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true) // Ritorna alla home dopo il login
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
            );

        return http.build();
    }

    // Algoritmo di crittografia per password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}