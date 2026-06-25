-- 1. UTENTI
-- La password crittografata corrisponde a: password123
insert into utente (id, username, password, ruolo) values (nextval('utente_seq'), 'mario_rossi', '$2a$10$cR59x2pyLzZr/UlSDqCF/eoj6JwgDWBRsH8s2ULYhZAhVdPpY7Nda', 'USER');

-- La password crittografata corrisponde a: adminpass
insert into utente (id, username, password, ruolo) values (nextval('utente_seq'), 'admin', '$2a$10$yOeTuH2L89xdTRSJVG2aZOWn8KhqSTd4wWzChDqDPZmJ9rorSdcyO', 'ADMIN');

-- 2. SQUADRE
insert into squadra (id, nome, città, anno_di_fondazione) values (nextval('squadra_seq'), 'Roma', 'Roma', 1927);
insert into squadra (id, nome, città, anno_di_fondazione) values (nextval('squadra_seq'), 'Lazio', 'Roma', 1900);
insert into squadra (id, nome, città, anno_di_fondazione) values (nextval('squadra_seq'), 'Juventus', 'Torino', 1897);
insert into squadra (id, nome, città, anno_di_fondazione) values (nextval('squadra_seq'), 'Milan', 'Milano', 1899);
insert into squadra (id, nome, città, anno_di_fondazione) values (nextval('squadra_seq'), 'Inter', 'Milano', 1908);
insert into squadra (id, nome, città, anno_di_fondazione) values (nextval('squadra_seq'), 'Napoli', 'Napoli', 1926);
insert into squadra (id, nome, città, anno_di_fondazione) values (nextval('squadra_seq'), 'Fiorentina', 'Firenze', 1926);
insert into squadra (id, nome, città, anno_di_fondazione) values (nextval('squadra_seq'), 'Atalanta', 'Bergamo', 1907);

-- 3. GIOCATORI
insert into giocatore (id, nome, cognome, ruolo, data_di_nascita, altezza, numero_tessera, squadra_id) values (nextval('giocatore_seq'), 'Francesco', 'Totti', 'Attaccante', '1976-09-27', 1.80, 101010, (SELECT id FROM squadra WHERE nome = 'Roma'));
insert into giocatore (id, nome, cognome, ruolo, data_di_nascita, altezza, numero_tessera, squadra_id) values (nextval('giocatore_seq'), 'Alessandro', 'Del Piero', 'Attaccante', '1974-11-09', 1.74, 101, (SELECT id FROM squadra WHERE nome = 'Juventus'));
insert into giocatore (id, nome, cognome, ruolo, data_di_nascita, altezza, numero_tessera, squadra_id) values (nextval('giocatore_seq'), 'Gianluigi', 'Buffon', 'Portiere', '1978-01-28', 1.92, 102, (SELECT id FROM squadra WHERE nome = 'Juventus'));
insert into giocatore (id, nome, cognome, ruolo, data_di_nascita, altezza, numero_tessera, squadra_id) values (nextval('giocatore_seq'), 'Paolo', 'Maldini', 'Difensore', '1968-06-26', 1.86, 201, (SELECT id FROM squadra WHERE nome = 'Milan'));
insert into giocatore (id, nome, cognome, ruolo, data_di_nascita, altezza, numero_tessera, squadra_id) values (nextval('giocatore_seq'), 'Ricardo', 'Kakà', 'Centrocampista', '1982-04-22', 1.86, 202, (SELECT id FROM squadra WHERE nome = 'Milan'));
insert into giocatore (id, nome, cognome, ruolo, data_di_nascita, altezza, numero_tessera, squadra_id) values (nextval('giocatore_seq'), 'Javier', 'Zanetti', 'Difensore', '1973-08-10', 1.78, 301, (SELECT id FROM squadra WHERE nome = 'Inter'));
insert into giocatore (id, nome, cognome, ruolo, data_di_nascita, altezza, numero_tessera, squadra_id) values (nextval('giocatore_seq'), 'Diego', 'Milito', 'Attaccante', '1979-06-12', 1.83, 302, (SELECT id FROM squadra WHERE nome = 'Inter'));
insert into giocatore (id, nome, cognome, ruolo, data_di_nascita, altezza, numero_tessera, squadra_id) values (nextval('giocatore_seq'), 'Diego Armando', 'Maradona', 'Attaccante', '1960-10-30', 1.65, 401, (SELECT id FROM squadra WHERE nome = 'Napoli'));
insert into giocatore (id, nome, cognome, ruolo, data_di_nascita, altezza, numero_tessera, squadra_id) values (nextval('giocatore_seq'), 'Marek', 'Hamsik', 'Centrocampista', '1987-07-27', 1.83, 402, (SELECT id FROM squadra WHERE nome = 'Napoli'));
insert into giocatore (id, nome, cognome, ruolo, data_di_nascita, altezza, numero_tessera, squadra_id) values (nextval('giocatore_seq'), 'Gabriel', 'Batistuta', 'Attaccante', '1969-02-01', 1.85, 501, (SELECT id FROM squadra WHERE nome = 'Fiorentina'));
insert into giocatore (id, nome, cognome, ruolo, data_di_nascita, altezza, numero_tessera, squadra_id) values (nextval('giocatore_seq'), 'Gianfranco', 'Zola', 'Attaccante', '1966-07-05', 1.68, 502, (SELECT id FROM squadra WHERE nome = 'Napoli'));
insert into giocatore (id, nome, cognome, ruolo, data_di_nascita, altezza, numero_tessera, squadra_id) values (nextval('giocatore_seq'), 'Cristiano', 'Ronaldo', 'Attaccante', '1985-02-05', 1.87, 103, (SELECT id FROM squadra WHERE nome = 'Juventus'));
insert into giocatore (id, nome, cognome, ruolo, data_di_nascita, altezza, numero_tessera, squadra_id) values (nextval('giocatore_seq'), 'Zlatan', 'Ibrahimovic', 'Attaccante', '1981-10-03', 1.95, 203, (SELECT id FROM squadra WHERE nome = 'Milan'));
insert into giocatore (id, nome, cognome, ruolo, data_di_nascita, altezza, numero_tessera, squadra_id) values (nextval('giocatore_seq'), 'Roberto', 'Baggio', 'Attaccante', '1967-02-18', 1.74, 104, (SELECT id FROM squadra WHERE nome = 'Juventus'));

-- 4. ARBITRI
insert into arbitro (id, nome, cognome, codice_arbitrale) values (nextval('arbitro_seq'), 'Pierluigi', 'Collina', 123456);
insert into arbitro (id, nome, cognome, codice_arbitrale) values (nextval('arbitro_seq'), 'Nicola', 'Rizzoli', 654321);
insert into arbitro (id, nome, cognome, codice_arbitrale) values (nextval('arbitro_seq'), 'Daniele', 'Orsato', 111222);
insert into arbitro (id, nome, cognome, codice_arbitrale) values (nextval('arbitro_seq'), 'Gianluca', 'Rocchi', 333444);

-- 5. TORNEI
insert into torneo (id, nome, anno, descrizione) values (nextval('torneo_seq'), 'Serie A', 2024, 'Campionato Italiano di Serie A');
insert into torneo (id, nome, anno, descrizione) values (nextval('torneo_seq'), 'Coppa Italia', 2024, 'Coppa Italia 2024');
insert into torneo (id, nome, anno, descrizione) values (nextval('torneo_seq'), 'Champions League', 2024, 'UEFA Champions League 2023-24');
insert into torneo (id, nome, anno, descrizione) values (nextval('torneo_seq'), 'Supercoppa Italiana', 2024, 'Supercoppa 2024 in Arabia');

-- 6. PARTITE (Tutto su una riga!)
insert into partita (id, luogo, data_ora, goals_home, goals_away, stato, squadra_casa_id, squadra_trasferta_id) values (nextval('partita_seq'), 'Stadio Olimpico', '2024-05-15 20:45:00', 2, 1, 'GIOCATA', (SELECT id FROM squadra WHERE nome = 'Roma'), (SELECT id FROM squadra WHERE nome = 'Lazio'));
insert into partita (id, luogo, data_ora, goals_home, goals_away, stato, squadra_casa_id, squadra_trasferta_id) values (nextval('partita_seq'), 'Allianz Stadium', '2024-05-20 21:00:00', 3, 2, 'GIOCATA', (SELECT id FROM squadra WHERE nome = 'Juventus'), (SELECT id FROM squadra WHERE nome = 'Milan'));
insert into partita (id, luogo, data_ora, goals_home, goals_away, stato, squadra_casa_id, squadra_trasferta_id) values (nextval('partita_seq'), 'San Siro', '2024-04-22 20:45:00', 1, 2, 'GIOCATA', (SELECT id FROM squadra WHERE nome = 'Milan'), (SELECT id FROM squadra WHERE nome = 'Inter'));
insert into partita (id, luogo, data_ora, goals_home, goals_away, stato, squadra_casa_id, squadra_trasferta_id) values (nextval('partita_seq'), 'Stadio Maradona', '2024-03-03 20:45:00', 2, 1, 'GIOCATA', (SELECT id FROM squadra WHERE nome = 'Napoli'), (SELECT id FROM squadra WHERE nome = 'Juventus'));
insert into partita (id, luogo, data_ora, goals_home, goals_away, stato, squadra_casa_id, squadra_trasferta_id) values (nextval('partita_seq'), 'Artemio Franchi', '2024-02-11 15:00:00', 1, 1, 'GIOCATA', (SELECT id FROM squadra WHERE nome = 'Fiorentina'), (SELECT id FROM squadra WHERE nome = 'Atalanta'));

-- 7. COMMENTI (Tutto su una riga!)
insert into commento (id, testo, data_ora, autore_id, partita_riferimento_id) values (nextval('commento_seq'), 'Che partita spettacolare, forza ragazzi!', '2024-05-15 22:50:00', (SELECT id FROM utente WHERE username = 'mario_rossi'), (SELECT id FROM partita WHERE luogo = 'Stadio Olimpico'));
insert into commento (id, testo, data_ora, autore_id, partita_riferimento_id) values (nextval('commento_seq'), 'Derby pazzesco!', '2024-04-22 22:50:00', (SELECT id FROM utente WHERE username = 'mario_rossi'), (SELECT id FROM partita WHERE luogo = 'San Siro'));