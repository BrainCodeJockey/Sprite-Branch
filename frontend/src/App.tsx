import { useState, useEffect } from 'react';
import axios from 'axios';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import CharacterSelection from './pages/CharacterSelectionPage/CharacterSelection';
import Ready from './pages/ReadyPage/Ready';
import StartPage from './pages/StartPage/StartPage';
import SceneOnePage from './pages/SceneOnePage/SceneOnePage';
import SceneTwoPage from './pages/SceneTwoPage/SceneTwoPage';
import SceneThreePage from './pages/SceneThreePage/SceneThreePage';
import SceneGoalPage from "./pages/SceneGoalPage/SceneGoalPage";
import { HeroDto, SaveGameDto } from "./types/types.ts";

export default function App() {
    const [saveGames, setSaveGames] = useState<SaveGameDto[]>([]);
    const [heroes, setHeroes] = useState<HeroDto[]>([]);
    const [error, setError] = useState<string | null>(null);
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
            .catch((error) => {
                console.error('Fehler beim Laden der Daten:', error);
            });

        if (shouldUpdate) {
            fetchDataAndUpdate()
                .then(() => {
                    setShouldUpdate(false);
                })
                .catch((error) => {
                    console.error('Fehler beim Laden der Daten:', error);
                });
        }
    }, [shouldUpdate]);

    return (
        <Router>
            <Routes>
                <Route path="/" element={<Ready />} />
                <Route path="/character-selection" element={
                    <CharacterSelection
                        saveGames={saveGames}
                        setSaveGames={setSaveGames}
                        heroes={heroes}
                        setHeroes={setHeroes}
                        error={error}
                        setError={setError}
                        setShouldUpdate={setShouldUpdate}
                    />
                } />
                <Route path="/start" element={<StartPage />} />
                <Route path="/berge" element={<SceneOnePage />} />
                <Route path="/schloss" element={<SceneTwoPage />} />
                <Route path="/unbekannt" element={<SceneThreePage />} />
                <Route path="/goal" element={<SceneGoalPage />} />
            </Routes>
        </Router>
    );
}
