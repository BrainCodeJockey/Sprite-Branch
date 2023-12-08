import React, { useState } from 'react';
import CharacterList from '../../components/CharacterList/CharacterList';
import AddHero from '../../components/AddHero/AddHero';
import UpdateHero from '../../components/UpdateHero/UpdateHero';
import { HeroDto, CharacterSelectionProps } from '../../types/types';
import './CharacterSelection.css';
import './../../App.css';
import backgroundImage from '../../assets/CharacterSelection.png';

const CharacterSelection: React.FC<CharacterSelectionProps> = ({
                                                                   saveGames,
                                                                   setSaveGames,
                                                                   heroes,
                                                                   setHeroes,
                                                                   error,
                                                                   setShouldUpdate,
                                                               }) => {
    const [addingHero, setAddingHero] = useState(false);
    const [updatingHero, setUpdatingHero] = useState<HeroDto | null>(null);
    const [updatingHeroDialogOpen, setUpdatingHeroDialogOpen] = useState(false);

    const onAddHero = (newHero: HeroDto) => {
        setHeroes((prevHeroes) => [...prevHeroes, newHero]);
        setAddingHero(false);
        setShouldUpdate(true);
    };
    const handleDeleteSaveGame = (saveId: string) => {
        setSaveGames((prevSaveGames) =>
            prevSaveGames.filter((game) => game.saveId !== saveId)
        );
        setHeroes((prevHeroes) => prevHeroes.filter((hero) => hero.id !== saveId));
    };

    const handleAddNewHero = () => {
        setAddingHero(true);
    };

    const handleUpdateHero = (hero: HeroDto | undefined) => {
        if (hero) {
            setUpdatingHero(hero);
            setUpdatingHeroDialogOpen(true);
        }
    };

    if (error) {
        return <section className="error-message">Fehler: {error}</section>;
    }

    return (
        <section style={{backgroundImage: `url(${backgroundImage})`}} className="character-selection-container">

            <h1>Saved Games</h1>
            <CharacterList
                saveGames={saveGames}
                heroes={heroes}
                onDeleteHero={handleDeleteSaveGame}
                onDeleteSaveGame={handleDeleteSaveGame}
                onUpdateHeroClick={handleUpdateHero}
            />
            {addingHero && (
                <AddHero
                    onAddHero={onAddHero}
                    setShouldUpdate={setShouldUpdate}
                    onClose={() => setAddingHero(false)}
                />
            )}
            {updatingHeroDialogOpen && updatingHero && (
                <UpdateHero
                    hero={updatingHero}
                    onUpdateHero={(updatedHero) => {
                        setHeroes(prevHeroes => {
                            return prevHeroes.map(hero => {
                                return hero.id === updatedHero.id ? updatedHero : hero;
                            });
                        });
                        setUpdatingHeroDialogOpen(false);
                    }}
                    onClose={() => setUpdatingHeroDialogOpen(false)}
                />
            )}
            <button className="add-hero-button" onClick={handleAddNewHero}>
                Neuen Held erstellen
            </button>
        </section>
    );
};
export default CharacterSelection;
