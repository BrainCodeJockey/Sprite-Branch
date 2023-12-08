import React, { useState } from 'react';
import axios from 'axios';
import { SaveGameDto } from '../../types/types';
import './DeleteSaveGame.css';

interface DeleteSaveGameProps {
    saveGame: SaveGameDto;
    onDeleteSaveGame: (saveId: string) => void;
    onDeleteHero: (heroId: string) => void;
}

const DeleteSaveGame: React.FC<DeleteSaveGameProps> = ({ saveGame, onDeleteSaveGame, onDeleteHero }) => {
    const [isDeleting, setIsDeleting] = useState(false);
    const [errorMessage, setErrorMessage] = useState<string | null>(null);

    const deleteThisSaveGame = async () => {
        setIsDeleting(true);
        try {
            await axios.delete(`/api/savegames/${saveGame.saveId}`);
            onDeleteSaveGame(saveGame.saveId);

            await axios.delete(`/api/heroes/${saveGame.heroId}`);
            onDeleteHero(saveGame.heroId);
        } catch (error) {
            console.error('Error when deleting the save game or hero:', error);
            setErrorMessage('Error while deleting the save game or hero.');
        }
        setIsDeleting(false);
    };

    return (
        <div className="delete-save-game-container">
            {errorMessage && <div className="error-message">{errorMessage}</div>}
            <button
                className="delete-save-game-button"
                onClick={deleteThisSaveGame}
                disabled={isDeleting}
            >
                {isDeleting ? 'Deleting...' : 'Delete'}
            </button>
        </div>
    );
};

export default DeleteSaveGame;
