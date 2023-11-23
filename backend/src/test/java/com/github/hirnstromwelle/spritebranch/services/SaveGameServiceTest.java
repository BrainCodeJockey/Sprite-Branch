package com.github.hirnstromwelle.spritebranch.services;
import com.github.hirnstromwelle.spritebranch.models.SaveGame;
import com.github.hirnstromwelle.spritebranch.repositorys.SaveGameRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SaveGameServiceTest {

    @InjectMocks
    private SaveGameService saveGameService;

    @Mock
    private SaveGameRepository saveGameRepository;

    @Test
    void getAllSaveGamesShouldReturnListOfSaveGames() {
        // GIVEN
        List<SaveGame> expectedSaveGames = List.of(
                new SaveGame("1", "Hero1", "State1"),
                new SaveGame("2", "Hero2", "State2")
        );

        // WHEN
        when(saveGameRepository.findAll()).thenReturn(expectedSaveGames);
        List<SaveGame> actualSaveGames = saveGameService.getAllSaveGames();

        // THEN
        verify(saveGameRepository).findAll();
        assertEquals(expectedSaveGames, actualSaveGames);
    }

    @Test
    void saveSaveGameShouldReturnSavedSaveGame() {
        // GIVEN
        SaveGame saveGameToSave = new SaveGame("1", "Hero1", "State1");
        SaveGame savedSaveGame = new SaveGame("1", "Hero1", "State1");

        // WHEN
        when(saveGameRepository.save(any(SaveGame.class))).thenReturn(savedSaveGame);
        SaveGame actualSaveGame = saveGameService.saveSaveGame(saveGameToSave);

        // THEN
        verify(saveGameRepository).save(saveGameToSave);
        assertEquals(savedSaveGame, actualSaveGame);
    }
}
