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
