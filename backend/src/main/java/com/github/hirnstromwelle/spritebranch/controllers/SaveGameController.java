package com.github.hirnstromwelle.spritebranch.controllers;
import com.github.hirnstromwelle.spritebranch.dto.SaveGameDto;
import com.github.hirnstromwelle.spritebranch.models.SaveGame;
import com.github.hirnstromwelle.spritebranch.services.SaveGameService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/savegames")
@RequiredArgsConstructor
public class SaveGameController {

    private final SaveGameService saveGameService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<SaveGameDto> getAllSaveGames() {
        List<SaveGame> saveGames = saveGameService.getAllSaveGames();
        return saveGames.stream()
                .map(saveGame -> modelMapper.map(saveGame, SaveGameDto.class))
                .toList();
    }
    @PostMapping
    public SaveGameDto createSaveGame(@Valid @RequestBody SaveGameDto saveGameDTO) {
        SaveGame saveGame = modelMapper.map(saveGameDTO, SaveGame.class);
        SaveGame savedSaveGame = saveGameService.saveSaveGame(saveGame);
        return modelMapper.map(savedSaveGame, SaveGameDto.class);
    }

    @PutMapping("/{saveId}")
    public ResponseEntity<SaveGameDto> updateSaveGame(@PathVariable String saveId, @Valid @RequestBody SaveGameDto saveGameDto) {
        SaveGame updatedSaveGame = modelMapper.map(saveGameDto, SaveGame.class);
        SaveGame saveGame = saveGameService.updateSaveGame(saveId, updatedSaveGame);
        return ResponseEntity.ok(modelMapper.map(saveGame, SaveGameDto.class));
    }

    @DeleteMapping("/{saveId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSaveGame(@PathVariable String saveId) {
        saveGameService.deleteSaveGame(saveId);
    }
}
