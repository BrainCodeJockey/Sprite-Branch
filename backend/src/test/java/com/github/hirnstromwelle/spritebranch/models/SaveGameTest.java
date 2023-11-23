package com.github.hirnstromwelle.spritebranch.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SaveGameTest {

    @Test
    void testSaveGame() {
        SaveGame saveGame = new SaveGame("save1", "hero1", "gameState1");
        assertEquals("save1", saveGame.getSaveId());
        assertEquals("hero1", saveGame.getHeroId());
        assertEquals("gameState1", saveGame.getSavedGameState());
    }
}
