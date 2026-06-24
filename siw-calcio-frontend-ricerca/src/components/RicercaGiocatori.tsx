import React, { useState, useEffect } from 'react';
import { 
    TextField, 
    CircularProgress, 
    List, 
    ListItem, 
    ListItemText, 
    Paper, 
    Typography,
    Alert,
    Box,
    Chip
} from '@mui/material';
import { getGiocatori } from '../services/giocatoreService';
import type { Giocatore } from '../types';

const RicercaGiocatori: React.FC = () => {
    const [giocatori, setGiocatori] = useState<Giocatore[]>([]);
    const [testoRicerca, setTestoRicerca] = useState<string>('');
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchGiocatori = async () => {
            try {
                const data = await getGiocatori();
                setGiocatori(data);
            } catch (err) {
                setError('Impossibile caricare i giocatori. Verifica che il server sia acceso.');
            } finally {
                setLoading(false);
            }
        };

        fetchGiocatori();
    }, []);

    const giocatoriFiltrati = (!testoRicerca || testoRicerca.trim() === "") 
        ? [] 
        : giocatori.filter(g => {
            const nomeStr = g.nome ? g.nome.toLowerCase() : "";
            const cognomeStr = g.cognome ? g.cognome.toLowerCase() : "";
            const nomeCompleto = `${nomeStr} ${cognomeStr}`;
            return nomeCompleto.includes(testoRicerca.toLowerCase());
        });

    return (
        <Paper elevation={3} sx={{ maxWidth: 800, margin: '40px auto', padding: '20px', textAlign: 'center' }}>
            <Typography variant="h4" component="h1" gutterBottom>
                Ricerca Giocatori
            </Typography>

            {error && (
                <Alert severity="error" sx={{ mb: 2 }}>
                    {error}
                </Alert>
            )}

            <TextField
                fullWidth
                label="Inizia a digitare un nome o un cognome..."
                variant="outlined"
                value={testoRicerca}
                onChange={(e) => setTestoRicerca(e.target.value)}
                sx={{ mb: 3 }}
            />

            {loading && !error && (
                <Box sx={{ display: 'flex', justifyContent: 'center', my: 4 }}>
                    <CircularProgress />
                </Box>
            )}

            {!loading && (
                <List>
                    {giocatoriFiltrati.map(g => (
                        <ListItem 
                            key={g.id} 
                            component="a" 
                            href={`/giocatori/${g.id}`} 
                            sx={{ 
                                borderBottom: '1px solid #eee',
                                textDecoration: 'none',
                                color: 'inherit',
                                '&:hover': { backgroundColor: '#f5f5f5' }
                            }}
                        >
                            <ListItemText 
                                primary={`${g.nome} ${g.cognome}`} 
                            />
                            {g.ruolo && (
                                <Chip label={g.ruolo} color="primary" size="small" />
                            )}
                        </ListItem>
                    ))}

                    {giocatoriFiltrati.length === 0 && giocatori.length > 0 && testoRicerca.trim() !== "" && (
                        <Typography color="error" sx={{ mt: 2, fontStyle: 'italic' }}>
                            Nessun giocatore trovato con questo nome.
                        </Typography>
                    )}
                </List>
            )}
        </Paper>
    );
};

export default RicercaGiocatori;
