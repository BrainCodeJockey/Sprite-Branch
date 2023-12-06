import React, { useState } from 'react';
import axios from 'axios';
import { HeroDto, SaveGameDto } from '../../types/types.ts';
import './AddHero.css'; // Import the CSS file

interface AddHeroProps {
    onAddHero: (newHero: HeroDto) => void;
    setShouldUpdate: (shouldUpdate: boolean) => void;
}

const AddHero: React.FC<AddHeroProps> = ({ onAddHero, setShouldUpdate }) => {
    const [newHeroName, setNewHeroName] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handleAddHeroSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

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
            setShouldUpdate(true);

        } catch (error) {
            console.error('Error when adding a hero or creating a save game:', error);
            setErrorMessage('Error while adding the hero or creating the save game.');
        }
    };

    return (
        <form className="add-hero-form" onSubmit={handleAddHeroSubmit}>
            {errorMessage && <div className="error-message">{errorMessage}</div>}
            <input
                className="hero-name-input"
                type="text"
                value={newHeroName}
                onChange={(e) => setNewHeroName(e.target.value)}
                placeholder="Hero Name"
            />
            <button className="add-hero-button" type="submit">Add Hero</button>
        </form>
    );
};

export default AddHero;
