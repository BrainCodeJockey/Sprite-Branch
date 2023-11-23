package com.github.hirnstromwelle.spritebranch.services;
import com.github.hirnstromwelle.spritebranch.models.SaveGame;
import com.github.hirnstromwelle.spritebranch.repositorys.SaveGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveGameService {

    private final SaveGameRepository saveGameRepository;

    public List<SaveGame> getAllSaveGames() {
        return saveGameRepository.findAll();
    }
    public SaveGame saveSaveGame(SaveGame saveGame) {
        return saveGameRepository.save(saveGame);
    }
}
