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
    @Test
    void testSaveGameDtoEqualsAndHashCode() {
        SaveGameDto saveGame1 = new SaveGameDto("1", "hero1", "gameState1");
        SaveGameDto saveGame2 = new SaveGameDto("1", "hero1", "gameState1");
        SaveGameDto saveGame3 = new SaveGameDto("2", "hero2", "gameState2");

        assertEquals(saveGame1, saveGame2);
        assertNotEquals(saveGame1, saveGame3);

        assertEquals(saveGame1.hashCode(), saveGame2.hashCode());
        assertNotEquals(saveGame1.hashCode(), saveGame3.hashCode());
    }

    @Test
    void testSaveGameDtoToString() {
        SaveGameDto saveGame = new SaveGameDto("1", "hero1", "gameState1");
        String expectedString = "SaveGameDto(saveId=1, heroId=hero1, savedGameState=gameState1)";

        assertEquals(expectedString, saveGame.toString());
    }
}
