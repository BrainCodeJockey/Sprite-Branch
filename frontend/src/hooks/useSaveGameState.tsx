import { useState, useEffect } from 'react';
import axios from 'axios';
import { SaveGameDto } from "../types/types.ts";

const useSaveGameState = (currentLocation: string) => {
    const [saveGame, setSaveGame] = useState<SaveGameDto>();

    useEffect(() => {
        if (!saveGame) return;

        // Aktualisieren Sie das savedGameState mit der neuen Location
        const updatedGameState = JSON.stringify({
            ...JSON.parse(saveGame.savedGameState),
            location: currentLocation
        });

        const newSaveGame = {
            ...saveGame,
            savedGameState: updatedGameState
        };

        setSaveGame(newSaveGame);

        const updateGameStateInBackend = async () => {
            try {
                const saveId = saveGame.saveId;
                await axios.put(`/api/savegames/${saveId}`, newSaveGame);
            } catch (error) {
                console.error('Fehler beim Aktualisieren des Spielstands', error);
            }
        };

        updateGameStateInBackend();
    }, [currentLocation, saveGame]);

    return saveGame;
};

export default useSaveGameState;
