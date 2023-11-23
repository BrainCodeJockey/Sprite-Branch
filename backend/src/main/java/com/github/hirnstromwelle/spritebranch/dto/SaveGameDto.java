package com.github.hirnstromwelle.spritebranch.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveGameDto {
    private String saveId;
    @NotNull
    private String heroId;
    @NotNull
    private String savedGameState;
}
