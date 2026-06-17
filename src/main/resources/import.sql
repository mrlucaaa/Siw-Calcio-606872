-- 1. UTENTI
-- La password crittografata corrisponde a: password123
insert into utente (id, username, password, ruolo) values (nextval('utente_seq'), 'mario_rossi', '$2a$10$cR59x2pyLzZr/UlSDqCF/eoj6JwgDWBRsH8s2ULYhZAhVdPpY7Nda', 'USER');

-- La password crittografata corrisponde a: adminpass
insert into utente (id, username, password, ruolo) values (nextval('utente_seq'), 'admin', '$2a$10$yOeTuH2L89xdTRSJVG2aZOWn8KhqSTd4wWzChDqDPZmJ9rorSdcyO', 'ADMIN');
-- 2. SQUADRE
insert into squadra (id, nome, città, anno_di_fondazione) values (nextval('squadra_seq'), 'Roma', 'Roma', 1927);
insert into squadra (id, nome, città, anno_di_fondazione) values (nextval('squadra_seq'), 'Lazio', 'Roma', 1900);

-- 3. GIOCATORI
insert into giocatore (id, nome, cognome, ruolo, data_di_nascita, altezza, numero_tessera) values (nextval('giocatore_seq'), 'Francesco', 'Totti', 'Attaccante', '1976-09-27', 1.80, 101010);

-- 4. ARBITRI
insert into arbitro (id, nome, cognome, codice_arbitrale) values (nextval('arbitro_seq'), 'Pierluigi', 'Collina', 123456);

-- 5. TORNEI
insert into torneo (id, nome, anno, descrizione) values (nextval('torneo_seq'), 'Serie A', 2024, 'Campionato Italiano di Serie A');

-- 6. PARTITE (Tutto su una riga!)
insert into partita (id, luogo, data_ora, goals_home, goals_away, stato, squadra_casa_id, squadra_trasferta_id) values (nextval('partita_seq'), 'Stadio Olimpico', '2024-05-15 20:45:00', 2, 1, 1, (SELECT id FROM squadra WHERE nome = 'Roma'), (SELECT id FROM squadra WHERE nome = 'Lazio'));

-- 7. COMMENTI (Tutto su una riga!)
insert into commento (id, testo, data_ora, autore_id, partita_riferimento_id) values (nextval('commento_seq'), 'Che partita spettacolare, forza ragazzi!', '2024-05-15 22:50:00', (SELECT id FROM utente WHERE username = 'mario_rossi'), (SELECT id FROM partita WHERE luogo = 'Stadio Olimpico'));