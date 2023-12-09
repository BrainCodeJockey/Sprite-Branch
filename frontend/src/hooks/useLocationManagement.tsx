import React, { useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import axios from 'axios';
import { SaveGameDto } from '../types/types.ts';

function useLocationManagement(
    saveGames: SaveGameDto[],
    setSaveGames: React.Dispatch<React.SetStateAction<SaveGameDto[]>>
) {
    const location = useLocation();

    useEffect(() => {
        const updateSavedGameState = async () => {
            try {
                const currentSaveGame = saveGames[0];
                if (currentSaveGame) {
                    await axios.put(`/api/savegames/${currentSaveGame.saveId}`, {
                        ...currentSaveGame,
                        savedGameState: location.pathname
                    });

                    setSaveGames((prevSaveGames) =>
                        prevSaveGames.map((game) =>
                            game.saveId === currentSaveGame.saveId
                                ? { ...game, savedGameState: location.pathname }
                                : game
                        )
                    );
                }
            } catch (error) {
                console.error('Fehler beim Aktualisieren des Standorts', error);
            }
        };

        updateSavedGameState();
    }, [location.pathname, saveGames, setSaveGames]);

    return location;
}

export default useLocationManagement;
