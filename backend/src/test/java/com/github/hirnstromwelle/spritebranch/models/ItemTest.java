package com.github.hirnstromwelle.spritebranch.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testItem() {
        Item item = new Item("1", "Sword", "Weapon", false);
        assertEquals("1", item.getId());
        assertEquals("Sword", item.getName());
        assertEquals("Weapon", item.getType());
        assertFalse(item.isFound());

        item.setFound(true);
        assertTrue(item.isFound());
    }
}
