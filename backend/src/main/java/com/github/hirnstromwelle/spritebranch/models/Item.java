package com.github.hirnstromwelle.spritebranch.models;
import lombok.Data;

@Data
public class Item {
    private String id;
    private String name;
    private String type;
    private boolean found;

    public void setFound(boolean found) {
        this.found = found;
    }
}
