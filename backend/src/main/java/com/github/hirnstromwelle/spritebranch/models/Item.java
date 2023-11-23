package com.github.hirnstromwelle.spritebranch.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String id;
    private String name;
    private String type;
    private boolean found;

    public void setFound(boolean found) {
        this.found = found;
    }
}
