import React, { useState } from 'react';
import axios from 'axios';
import { HeroDto, SaveGameDto } from '../../types/types.ts';
import './AddHero.css';

interface AddHeroProps {
    onAddHero: (newHero: HeroDto) => void;
    setShouldUpdate: (shouldUpdate: boolean) => void;
    onClose: () => void;
}

const AddHero: React.FC<AddHeroProps> = ({ onAddHero, setShouldUpdate, onClose }) => {
    const [newHeroName, setNewHeroName] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handleAddHeroSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        try {
            const response = await axios.post<HeroDto>('/api/heroes', { name: newHeroName });
            const newHero = response.data;
            onAddHero(newHero);

            await axios.post<SaveGameDto>('/api/savegames', {
                heroId: newHero.id,
                savedGameState: JSON.stringify({ location: "start" })
            });

            setNewHeroName('');
            setErrorMessage('');
            setShouldUpdate(true);

        } catch (error) {
            console.error('Error when adding a hero or creating a save game:', error);
            setErrorMessage('Error while adding the hero or creating the save game.');
        }
    };

    const handleCancel = () => {
        onClose();
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
            <button className="cancel-button" onClick={handleCancel}>Cancel</button>
        </form>
    );
};

export default AddHero;
