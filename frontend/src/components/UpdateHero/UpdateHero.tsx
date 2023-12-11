import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './UpdateHero.css';

interface HeroDto {
    id: string;
    name: string;
}

interface UpdateHeroProps {
    hero: HeroDto;
    onUpdateHero: (updatedHero: HeroDto) => void;
    onClose: () => void;
}

const UpdateHero: React.FC<UpdateHeroProps> = ({ hero, onUpdateHero, onClose }) => {
    const [updatedHeroName, setUpdatedHeroName] = useState<string>(hero.name);
    const [errorMessage, setErrorMessage] = useState<string>('');

    useEffect(() => {
        setUpdatedHeroName(hero.name);
    }, [hero]);

    const handleUpdateHeroSubmit = async () => {
        if (updatedHeroName.trim() === '') {
            setErrorMessage('Hero name cannot be empty');
            return;
        }

        try {
            const response = await axios.put<HeroDto>(
                `/api/heroes/${hero.id}`,
                { name: updatedHeroName }
            );
            const updatedHero: HeroDto = response.data;
            onUpdateHero(updatedHero);
            setErrorMessage('');
            onClose();
        } catch (error) {
            console.error('Error when updating the hero:', error);
            setErrorMessage('Error while updating the hero.');
        }
    };

    return (
        <form className="update-hero-form">
            <h2 className="update-hero-title">Update Hero</h2>
            {errorMessage && <div className="error-message">{errorMessage}</div>}
            <input
                className="update-hero-input"
                type="text"
                value={updatedHeroName}
                onChange={(e) => setUpdatedHeroName(e.target.value)}
                placeholder="Update Hero Name"
            />
            <button className="update-hero-button" onClick={handleUpdateHeroSubmit}>Update Hero</button>
            <button className="cancel-button" onClick={onClose}>Cancel</button>
        </form>
    );
};

export default UpdateHero;
