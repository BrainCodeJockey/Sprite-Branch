import { useState, useEffect } from 'react';
import axios from 'axios';

interface HeroDto {
    id: string;
    name: string;
    itemIds: Set<string>;
}

export default function ViewAllHeroes() {
    const [heroes, setHeroes] = useState<HeroDto[]>([]);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        axios.get('/api/heroes')
            .then(response => setHeroes(response.data as HeroDto[]))
            .catch(error => {
                console.error('Fehler beim Abrufen:', error);
                setError('Fehler beim Laden der Helden');
            });
    }, []);

    if (error) {
        return <div>Fehler: {error}</div>;
    }

    return (
        <div>
            <h1>Alle Helden</h1>
            <ul>
                {heroes.map(hero => (
                    <li key={hero.id}>
                        {hero.name} - Items: {Array.from(hero.itemIds).join(', ')}
                    </li>
                ))}
            </ul>
        </div>
    );
}
