import api from './api';
import type { Giocatore } from '../types';

export const getGiocatori = async (): Promise<Giocatore[]> => {
    try {
        const response = await api.get<Giocatore[]>('/giocatori');
        return response.data;
    } catch (error) {
        console.error("Errore nel recupero dei giocatori", error);
        throw error;
    }
};
