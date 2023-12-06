import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { CharacterList } from '../../components/CharacterList/CharacterList.tsx';
import AddHero from '../../components/AddHero/AddHero.tsx';
import UpdateHero from '../../components/UpdateHero/UpdateHero.tsx';
import { SaveGameDto, HeroDto } from '../../types/types.ts';
import './CharacterSelection.css';

const CharacterSelection: React.FC = () => {
    const [saveGames, setSaveGames] = useState<SaveGameDto[]>([]);
    const [heroes, setHeroes] = useState<HeroDto[]>([]);
    const [error, setError] = useState<string | null>(null);
    const [addingHero, setAddingHero] = useState(false);
    const [updatingHero, setUpdatingHero] = useState<HeroDto | null>(null);
    const [updatingHeroDialogOpen, setUpdatingHeroDialogOpen] = useState(false);
    const [shouldUpdate, setShouldUpdate] = useState(false);

    useEffect(() => {
        const fetchDataAndUpdate = async () => {
            try {
                const [saveGamesResponse, heroesResponse] = await Promise.all([
                    axios.get<SaveGameDto[]>('/api/savegames'),
                    axios.get<HeroDto[]>('/api/heroes')
                ]);

                setSaveGames(saveGamesResponse.data);
                setHeroes(heroesResponse.data);
                setError(null);
            } catch (error) {
                const typedError = error as Error;
                if (typedError.message) {
                    setError(`Fehler beim Laden der Daten: ${typedError.message}`);
                } else {
                    setError('Fehler beim Laden der Daten');
                }
            }
        };

        fetchDataAndUpdate()
            .then(() => {
                setShouldUpdate(false);
            })
            .catch((error) => {
                console.error('Fehler beim Laden der Daten:', error);
            });
    }, [shouldUpdate]);

    const onDeleteSaveGame = async (saveId: string) => {
        try {
            const saveGameToDelete = saveGames.find(game => game.saveId === saveId);
            if (!saveGameToDelete) {
                console.error('Savegame not found');
                return;
            }

            await Promise.all([
                axios.delete(`/api/savegames/${saveId}`),
                axios.delete(`/api/heroes/${saveGameToDelete.heroId}`)
            ]);

            setSaveGames(prevSaveGames => prevSaveGames.filter(game => game.saveId !== saveId));
            setHeroes(prevHeroes => prevHeroes.filter(hero => hero.id !== saveGameToDelete.heroId));
        } catch (error) {
            console.error('Error when deleting the save game or hero:', error);
        }
    };

    const onAddHero = (newHero: HeroDto) => {
        setHeroes(prevHeroes => [...prevHeroes, newHero]);
        setAddingHero(false);
        setShouldUpdate(true);
    };

    const handleAddHeroClick = () => {
        setAddingHero(true);
    };

    const handleUpdateHeroClick = (hero: HeroDto | undefined) => {
        if (hero) {
            setUpdatingHero(hero);
            setUpdatingHeroDialogOpen(true);
        }
    };

    if (error) {
        return <div className="error-message">Fehler: {error}</div>;
    }

    return (
        <div className="character-selection-container">
            <h1>Saved Games</h1>
            <CharacterList
                saveGames={saveGames}
                heroes={heroes}
                onDeleteSaveGame={onDeleteSaveGame}
                onUpdateHeroClick={handleUpdateHeroClick}
            />
            <button className="add-hero-button" onClick={handleAddHeroClick}>Neuen Held erstellen</button>
            {addingHero && <AddHero onAddHero={onAddHero} setShouldUpdate={setShouldUpdate} />}
            {updatingHeroDialogOpen && updatingHero && (
                <UpdateHero
                    hero={updatingHero}
                    onUpdateHero={updatedHero => {
                        setHeroes(prevHeroes => prevHeroes.map(hero => (hero.id === updatedHero.id ? updatedHero : hero)));
                        setUpdatingHeroDialogOpen(false);
                    }}
                    onClose={() => setUpdatingHeroDialogOpen(false)}
                />
            )}
        </div>
    );
};

export default CharacterSelection;
