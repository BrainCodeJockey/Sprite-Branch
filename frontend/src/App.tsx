import './App.css';
import Ready from './pages/ReadyPage/Ready.tsx';
import CharacterSelection from './pages/CharacterSelectionPage/CharacterSelection.tsx';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

export default function App() {
  return (
      <Router>
        <Routes>
          <Route path="/" element={<Ready />} />
          <Route path="/character-selection" element={<CharacterSelection />} />
        </Routes>
      </Router>
  );
}
