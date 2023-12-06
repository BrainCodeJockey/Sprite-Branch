package com.github.hirnstromwelle.spritebranch.services;
import com.github.hirnstromwelle.spritebranch.models.SaveGame;
import com.github.hirnstromwelle.spritebranch.repositorys.SaveGameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

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
    @Test
    void deleteSaveGameShouldDeleteSaveGame() {
        // GIVEN
        String saveIdToDelete = "1";
        SaveGame saveGameToDelete = new SaveGame(saveIdToDelete, "HeroToDelete", "StateToDelete");

        // WHEN
        when(saveGameRepository.findById(saveIdToDelete)).thenReturn(Optional.of(saveGameToDelete));
        saveGameService.deleteSaveGame(saveIdToDelete);

        // THEN
        verify(saveGameRepository).delete(saveGameToDelete);
    }
    @Test
    void updateSaveGameShouldUpdateAndReturnUpdatedSaveGame() {
        // GIVEN
        String saveGameIdToUpdate = "1";
        SaveGame originalSaveGame = new SaveGame(saveGameIdToUpdate, "Hero1", "State1");
        SaveGame updatedSaveGame = new SaveGame(saveGameIdToUpdate, "UpdatedHero", "UpdatedState");

        when(saveGameRepository.findById(saveGameIdToUpdate)).thenReturn(Optional.of(originalSaveGame));
        when(saveGameRepository.save(any(SaveGame.class))).thenReturn(updatedSaveGame);

        // WHEN
        SaveGame actualUpdatedSaveGame = saveGameService.updateSaveGame(saveGameIdToUpdate, updatedSaveGame);

        // THEN
        verify(saveGameRepository).findById(saveGameIdToUpdate);
        verify(saveGameRepository).save(any(SaveGame.class));
        assertEquals(updatedSaveGame.getHeroId(), actualUpdatedSaveGame.getHeroId());
        assertEquals(updatedSaveGame.getSavedGameState(), actualUpdatedSaveGame.getSavedGameState());
    }
}
