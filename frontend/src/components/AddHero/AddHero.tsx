import React, { useState } from 'react';
import axios from 'axios';
import { HeroDto, SaveGameDto } from '../../types/types.ts';

interface AddHeroProps {
    onAddHero: (newHero: HeroDto) => void;
}

const AddHero: React.FC<AddHeroProps> = ({ onAddHero }) => {
    const [newHeroName, setNewHeroName] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handleAddHeroSubmit = async () => {
        if (newHeroName.trim() === '') {
            setErrorMessage('Hero name cannot be empty');
            return;
        }

        try {
            const response = await axios.post<HeroDto>('/api/heroes', { name: newHeroName });
            const newHero = response.data;
            onAddHero(newHero);

            await axios.post<SaveGameDto>('/api/savegames', {
                heroId: newHero.id,
                savedGameState: JSON.stringify({ location: "Dunkler Wald" })
            });

            setNewHeroName('');
            setErrorMessage('');
        } catch (error) {
            console.error('Error when adding a hero or creating a save game:', error);
            setErrorMessage('Error while adding the hero or creating the save game.');

        }
    };

    return (
        <div>
            {errorMessage && <div className="error-message">{errorMessage}</div>}
            <input
                type="text"
                value={newHeroName}
                onChange={(e) => setNewHeroName(e.target.value)}
                placeholder="Hero Name"
            />
            <button onClick={handleAddHeroSubmit}>Add Hero</button>
        </div>
    );
};

export default AddHero;
