package com.github.hirnstromwelle.spritebranch.dto;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class SaveGameDto {
    private String saveId;
    @NotNull
    private String heroId;
    @NotNull
    private String savedGameState;
}
