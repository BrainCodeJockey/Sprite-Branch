import React from 'react';
import { CharacterListProps } from '../../types/types';
import DeleteSaveGame from '../DeleteSaveGame/DeleteSaveGame';
import { Link, useLocation } from 'react-router-dom';
import './CharacterList.css';
import './../../App.css';

const CharacterList: React.FC<CharacterListProps> = ({
                                                         saveGames,
                                                         heroes,
                                                         onDeleteSaveGame,
                                                         onDeleteHero,
                                                         onUpdateHeroClick,
                                                     }) => {
    const location = useLocation();

    return (
        <section className="CharacterListContainer">
            {saveGames.map(saveGame => {
                const hero = heroes.find(h => h.id === saveGame.heroId);

                return (
                    <article className="save-game-card-container" key={saveGame.saveId}>
                        <header>
                            <h2 className="hero-name">Name: {hero?.name ?? 'Unknown'}</h2>
                        </header>
                        <p className="location">Location: {location.pathname}</p>
                        <DeleteSaveGame
                            saveGame={saveGame}
                            onDeleteSaveGame={() => onDeleteSaveGame(saveGame.saveId)}
                            onDeleteHero={() => onDeleteHero(hero?.id ?? '')}
                        />
                        <button className="update-hero-button" onClick={() => onUpdateHeroClick(hero)}>
                            Update Hero
                        </button>
                            <Link to={location.pathname === '/character-selection' ? '/start' : location.pathname}>
                                <button className="start-button">
                                    Start
                                </button>
                            </Link>
                    </article>
                );
            })}
        </section>
    );
};

export default CharacterList;
