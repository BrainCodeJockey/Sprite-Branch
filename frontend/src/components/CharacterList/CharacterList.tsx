import React from 'react';
import { SaveGameDto, HeroDto } from '../../types/types.ts';
import { SaveGameCard } from '../SaveGameCard/SaveGameCard.tsx';
import './CharacterList.css';

interface CharacterListProps {
    saveGames: SaveGameDto[];
    heroes: HeroDto[];
    onDeleteSaveGame: (saveId: string) => void;
    onUpdateHeroClick: (hero: HeroDto | undefined) => void;
}

const CharacterList: React.FC<CharacterListProps> = ({ saveGames, heroes, onDeleteSaveGame, onUpdateHeroClick }) => {
    return (
        <div className="CharacterListContainer">
            {saveGames.map(saveGame => {
                const hero = heroes.find(hero => hero.id === saveGame.heroId);
                return (
                    <SaveGameCard
                        key={saveGame.saveId}
                        saveGame={saveGame}
                        hero={hero}
                        onDeleteSaveGame={onDeleteSaveGame}
                        onUpdateHeroClick={() => onUpdateHeroClick(hero)}
                    />
                );
            })}
        </div>
    );
};

export { CharacterList };
