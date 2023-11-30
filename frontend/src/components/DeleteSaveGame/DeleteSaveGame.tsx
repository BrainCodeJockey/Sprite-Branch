import React, { useState } from 'react';
import axios from 'axios';
import { SaveGameDto } from '../../types/types.ts';

interface DeleteSaveGameProps {
    saveGame: SaveGameDto;
    onDeleteSaveGame: (saveId: string) => void;
}

const DeleteSaveGame: React.FC<DeleteSaveGameProps> = ({ saveGame, onDeleteSaveGame }) => {
    const [isDeleting, setIsDeleting] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    const deleteThisSaveGame = async () => {
        setIsDeleting(true);
        try {
            await axios.delete(`/api/savegames/${saveGame.saveId}`);
            onDeleteSaveGame(saveGame.saveId);
        } catch (error) {
            console.error('Error parsing saved game state:', error);
            setErrorMessage('Invalid location data');
        }
        setIsDeleting(false);
    };

    return (
        <div>
            {errorMessage && <div className="error-message">{errorMessage}</div>}
            <button onClick={deleteThisSaveGame} disabled={isDeleting}>
                {isDeleting ? 'delete...' : 'Delete'}
            </button>
        </div>
    );
};

export default DeleteSaveGame;
