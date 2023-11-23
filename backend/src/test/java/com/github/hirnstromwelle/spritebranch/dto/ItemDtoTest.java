package com.github.hirnstromwelle.spritebranch.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemDtoTest {

    @Test
    void testItemDto() {
        ItemDto item = new ItemDto("1", "Sword", "Weapon", false);
        assertEquals("1", item.getId());
        assertEquals("Sword", item.getName());
        assertEquals("Weapon", item.getType());
        assertFalse(item.isFound());

        item.setFound(true);
        assertTrue(item.isFound());
    }
}
