import './App.css';
import Ready from './pages/ReadyPage/Ready';
import CharacterSelection from './pages/CharacterSelectionPage/CharacterSelection';
import StartPage from './pages/StartPage/StartPage';
// import SceneOnePage from './pages/SceneOnePage/SceneOnePage';
// import SceneTwoPage from './pages/SceneTwoPage/SceneTwoPage';
// import SceneThreePage from './pages/SceneThreePage/SceneThreePage';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

export default function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Ready />} />
                <Route path="/character-selection" element={<CharacterSelection />} />
                <Route path="/start" element={<StartPage />} />
                {/*<Route path="/berge" element={<SceneOnePage />*/}
                {/*<Route path="/burg" element={<SceneTwoPage />*/}
                {/*<Route path="/unbekannt" element={<SceneThreePage />*/}
            </Routes>
        </Router>
    );
}
