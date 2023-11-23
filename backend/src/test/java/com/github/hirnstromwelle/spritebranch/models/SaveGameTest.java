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
    @Test
    void testSaveGameEqualsAndHashCode() {
        SaveGame saveGame1 = new SaveGame("save1", "hero1", "gameState1");
        SaveGame saveGame2 = new SaveGame("save1", "hero1", "gameState1");
        SaveGame saveGame3 = new SaveGame("2", "hero2", "gameState2");

        assertEquals(saveGame1, saveGame2);
        assertNotEquals(saveGame1, saveGame3);

        assertEquals(saveGame1.hashCode(), saveGame2.hashCode());
        assertNotEquals(saveGame1.hashCode(), saveGame3.hashCode());
    }

    @Test
    void testSaveGameToString() {
        SaveGame saveGame = new SaveGame("save1", "hero1", "gameState1");
        assertNotNull(saveGame.toString());
    }
}
