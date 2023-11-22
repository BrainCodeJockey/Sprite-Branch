package com.github.hirnstromwelle.spritebranch.models;
import lombok.Data;

@Data
public class SaveGame {
    private String saveId;
    private String heroId;
    private String savedGameState;
}
