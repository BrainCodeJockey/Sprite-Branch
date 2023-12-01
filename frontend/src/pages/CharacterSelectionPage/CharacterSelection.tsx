import React, { useState, useEffect } from 'react';
import axios from 'axios';
import DeleteSaveGame from '../../components/DeleteSaveGame/DeleteSaveGame.tsx';
import AddHero from '../../components/AddHero/AddHero.tsx';
import { getLocationFromSavedGameState } from '../../components/utils/getLocationFromSavedGameState.ts';
import { SaveGameDto, HeroDto } from '../../types/types.ts';
import './CharacterSelection.css';

const CharacterSelection: React.FC = () => {
    const [saveGames, setSaveGames] = useState<SaveGameDto[]>([]);
    const [heroes, setHeroes] = useState<HeroDto[]>([]);
    const [error, setError] = useState<string | null>(null);
    const [addingHero, setAddingHero] = useState(false);

    useEffect(() => {
        const fetchSaveGames = axios.get<SaveGameDto[]>('/api/savegames');
        const fetchHeroes = axios.get<HeroDto[]>('/api/heroes');

        Promise.all([fetchSaveGames, fetchHeroes])
            .then(([saveGamesResponse, heroesResponse]) => {
                setSaveGames(saveGamesResponse.data);
                setHeroes(heroesResponse.data);
            })
            .catch(error => {
                setError('Fehler beim Laden der Daten: ' + error.message);
            });
    }, []);

    const onDeleteSaveGame = (saveId: string) => {
        const saveGameToDelete = saveGames.find(game => game.saveId === saveId);
        if (!saveGameToDelete) {
            console.error('Savegame not found');
            return;
        }

        axios.delete(`/api/savegames/${saveId}`)
            .then(() => {
                setSaveGames(prevSaveGames => prevSaveGames.filter(game => game.saveId !== saveId));
                // Löschen Sie auch den zugehörigen Helden
                return axios.delete(`/api/heroes/${saveGameToDelete.heroId}`);
            })
            .then(() => {
                setHeroes(prevHeroes => prevHeroes.filter(hero => hero.id !== saveGameToDelete.heroId));
            })
            .catch(error => {
                console.error('Error when deleting the save game or hero:', error);
            });
    };

    const onAddHero = (newHero: HeroDto) => {
        setHeroes(prevHeroes => [...prevHeroes, newHero]);
        setAddingHero(false);
    };

    const handleAddHeroClick = () => {
        setAddingHero(true);
    };

    if (error) {
        return <div>Fehler: {error}</div>;
    }

    return (
        <>
            <h1>Saved Games</h1>
            <div className="save-game-grid">
                {saveGames.map(saveGame => (
                    <div key={saveGame.saveId} className="save-game-card">
                        <p>Name: {heroes.find(hero => hero.id === saveGame.heroId)?.name ?? 'Unknown'}</p>
                        <p>Location: {getLocationFromSavedGameState(saveGame.savedGameState)}</p>
                        <DeleteSaveGame saveGame={saveGame} onDeleteSaveGame={onDeleteSaveGame} />
                    </div>
                ))}
            </div>

            <button onClick={handleAddHeroClick}>Neuen Held erstellen</button>

            {addingHero && (
                <AddHero onAddHero={onAddHero} />
            )}
        </>
    );
};

export default CharacterSelection;
