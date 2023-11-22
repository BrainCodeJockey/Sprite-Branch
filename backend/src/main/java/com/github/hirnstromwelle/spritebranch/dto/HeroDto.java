package com.github.hirnstromwelle.spritebranch.dto;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
@Data
public class HeroDto {
    private String id;
    @NotNull
    @Size(min = 1, max = 20)
    private String name;
    private Set<String> itemIds;
}
