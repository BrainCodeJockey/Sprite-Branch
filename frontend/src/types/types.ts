import { Dispatch, SetStateAction } from 'react';

export interface SaveGameDto {
    saveId: string;
    heroId: string;
    savedGameState: string;
}

export interface HeroDto {
    id: string;
    name: string;
}

export interface GameState {
    location?: string;
}

export interface CharacterSelectionProps {
    saveGames: SaveGameDto[];
    setSaveGames: Dispatch<SetStateAction<SaveGameDto[]>>;
    heroes: HeroDto[];
    setHeroes: Dispatch<SetStateAction<HeroDto[]>>;
    error: string | null;
    setError: Dispatch<SetStateAction<string | null>>;
    setShouldUpdate: Dispatch<SetStateAction<boolean>>;
}

export interface CharacterListProps {
    saveGames: SaveGameDto[];
    heroes: HeroDto[];
    onDeleteSaveGame: (saveId: string) => void;
    onDeleteHero: (heroId: string) => void;
    onUpdateHeroClick: (hero: HeroDto | undefined) => void;
}
