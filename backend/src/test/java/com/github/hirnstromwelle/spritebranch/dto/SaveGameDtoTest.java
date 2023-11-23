package com.github.hirnstromwelle.spritebranch.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SaveGameDtoTest {

    @Test
    void testSaveGameDto() {
        SaveGameDto saveGame = new SaveGameDto("save1", "hero1", "gameState1");
        assertEquals("save1", saveGame.getSaveId());
        assertEquals("hero1", saveGame.getHeroId());
        assertEquals("gameState1", saveGame.getSavedGameState());
    }
}
