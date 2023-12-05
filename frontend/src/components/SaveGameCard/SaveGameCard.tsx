import React from 'react';
import { SaveGameDto, HeroDto } from '../../types/types.ts';
import DeleteSaveGame from '../DeleteSaveGame/DeleteSaveGame.tsx';
import { getLocationFromSavedGameState } from '../utils/getLocationFromSavedGameState.ts';
import './SaveGameCard.css';

interface SaveGameCardProps {
    saveGame: SaveGameDto;
    hero: HeroDto | undefined;
    onDeleteSaveGame: (saveId: string) => void;
    onUpdateHeroClick: () => void;
}

const SaveGameCard: React.FC<SaveGameCardProps> = ({ saveGame, hero, onDeleteSaveGame, onUpdateHeroClick }) => {
    return (
        <div className="save-game-card-container">
            <p className="hero-name">Name: {hero?.name ?? 'Unknown'}</p>
            <p className="location">Location: {getLocationFromSavedGameState(saveGame.savedGameState)}</p>
            <DeleteSaveGame saveGame={saveGame} onDeleteSaveGame={() => onDeleteSaveGame(saveGame.saveId)} />
            <button className="update-hero-button" onClick={onUpdateHeroClick}>Update Hero</button>
        </div>
    );
};

export { SaveGameCard };
