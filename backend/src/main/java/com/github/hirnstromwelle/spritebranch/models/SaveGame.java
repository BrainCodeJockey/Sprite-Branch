package com.github.hirnstromwelle.spritebranch.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveGame {
    @Id
    private String saveId;
    private String heroId;
    private String savedGameState;
}
