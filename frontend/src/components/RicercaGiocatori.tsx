import { useState, useEffect } from 'react';

interface Squadra {
  id: number;
  nome: string;
}

interface Giocatore {
  id: number;
  nome: string;
  cognome: string;
  ruolo: string;
  altezza: number;
  numeroTessera: string;
  squadra?: Squadra;
}

const RicercaGiocatori = () => {
  const [giocatori, setGiocatori] = useState<Giocatore[]>([]);
  const [testoRicerca, setTestoRicerca] = useState("");
  const [caricamento, setCaricamento] = useState(true);

  // Al caricamento della pagina, facciamo la chiamata al backend Spring Boot
  useEffect(() => {
    fetch('/api/giocatori')
      .then(response => response.json())
      .then(data => {
        setGiocatori(data);
        setCaricamento(false);
      })
      .catch(error => {
        console.error("Errore nel caricamento dei giocatori:", error);
        setCaricamento(false);
      });
  }, []);

  // Filtra i giocatori in base a ciò che l'utente scrive
  const giocatoriFiltrati = giocatori.filter(g =>
    g.nome.toLowerCase().includes(testoRicerca.toLowerCase()) ||
    g.cognome.toLowerCase().includes(testoRicerca.toLowerCase()) ||
    g.ruolo.toLowerCase().includes(testoRicerca.toLowerCase()) ||
    (g.squadra && g.squadra.nome.toLowerCase().includes(testoRicerca.toLowerCase()))
  );

  return (
    <div style={{ maxWidth: '800px', margin: '40px auto', backgroundColor: '#ffffff', color: '#333333', fontFamily: 'serif' }}>
      
      {/* Intestazione e Link per tornare indietro */}
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '20px' }}>
        <a href="/" style={{ color: '#666', textDecoration: 'none', fontSize: '14px' }}>
          &larr; Torna alla Home
        </a>
      </div>

      <div style={{ marginBottom: '30px' }}>
        <input 
          type="text" 
          placeholder="Cerca giocatore..." 
          value={testoRicerca}
          onChange={(e) => setTestoRicerca(e.target.value)}
          style={{
            width: '100%',
            padding: '10px',
            fontSize: '16px',
            border: '1px solid #ccc',
            borderRadius: '4px',
            backgroundColor: '#fff',
            color: '#000'
          }}
        />
      </div>

      {caricamento ? (
        <div style={{ padding: '20px', textAlign: 'center', color: '#666' }}>Caricamento giocatori in corso...</div>
      ) : (
        <ul style={{ listStyleType: 'none', padding: 0, margin: 0 }}>
          {giocatoriFiltrati.length > 0 ? (
            giocatoriFiltrati.map(g => (
              <li key={g.id} style={{ 
                borderBottom: '1px solid #ddd', 
                padding: '15px 0',
                fontSize: '18px'
              }}>
                <a href={`/giocatori/${g.id}`} style={{ textDecoration: 'none', display: 'block' }}>
                  <span style={{ fontWeight: 'bold', color: '#4b0082' }}>
                    {g.nome} {g.cognome}
                  </span>
                  {g.squadra && (
                    <span style={{ color: '#666', marginLeft: '8px' }}>
                      ({g.squadra.nome})
                    </span>
                  )}
                </a>
              </li>
            ))
          ) : (
            <li style={{ padding: '20px', textAlign: 'center', color: '#666' }}>
              Nessun giocatore trovato.
            </li>
          )}
        </ul>
      )}
    </div>
  );
}

export default RicercaGiocatori;
