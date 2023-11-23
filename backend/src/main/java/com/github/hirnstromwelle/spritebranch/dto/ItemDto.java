package com.github.hirnstromwelle.spritebranch.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String type;
    private boolean found;
}
