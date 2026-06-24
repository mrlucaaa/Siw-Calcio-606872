export interface Squadra {
    id: number;
    nome: string;
    annoDiFondazione: number;
    città: string;
}

export interface Giocatore {
    id: number;
    numeroTessera: number;
    nome: string;
    cognome: string;
    dataDiNascita: string;
    altezza: number;
    ruolo: string;
    squadra: Squadra | null;
}
