package com.github.hirnstromwelle.spritebranch.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hero {
    private String id;
    private String name;
    private Set<String> itemIds = new HashSet<>();

    public void addItem(String itemId) {
        this.itemIds.add(itemId);
    }

    public void removeItem(String itemId) {
        this.itemIds.remove(itemId);
    }

    public boolean hasItem(String itemId) {
        return this.itemIds.contains(itemId);
    }
}
