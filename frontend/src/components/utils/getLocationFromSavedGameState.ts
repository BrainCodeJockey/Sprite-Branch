import { GameState } from '../../types/types.ts';
export function getLocationFromSavedGameState(savedGameState: string): string {
    try {
        const gameState: GameState = JSON.parse(savedGameState);
        return gameState.location ?? "Dunkler Wald";
    } catch (error) {
        console.error("Fehler beim Parsen des gespeicherten Spielstands:", error);
        return "Fehlerhafte Location-Daten";
    }
}
