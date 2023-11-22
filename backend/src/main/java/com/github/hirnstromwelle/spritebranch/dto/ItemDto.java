package com.github.hirnstromwelle.spritebranch.dto;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class ItemDto {
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String type;
    private boolean found;
}
