package com.github.hirnstromwelle.spritebranch.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveGame {
    private String saveId;
    private String heroId;
    private String savedGameState;
}
