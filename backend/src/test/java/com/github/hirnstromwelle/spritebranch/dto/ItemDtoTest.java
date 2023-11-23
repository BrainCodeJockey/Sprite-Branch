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
    @Test
    void testItemDtoEqualsAndHashCode() {
        ItemDto item1 = new ItemDto("1", "Sword", "Weapon", false);
        ItemDto item2 = new ItemDto("1", "Sword", "Weapon", false);
        ItemDto item3 = new ItemDto("2", "Axe", "Weapon", false);

        assertEquals(item1, item2);
        assertNotEquals(item1, item3);

        assertEquals(item1.hashCode(), item2.hashCode());
        assertNotEquals(item1.hashCode(), item3.hashCode());
    }

    @Test
    void testItemDtoToString() {
        ItemDto item = new ItemDto("1", "Sword", "Weapon", false);
        String expectedString = "ItemDto(id=1, name=Sword, type=Weapon, found=false)";

        assertEquals(expectedString, item.toString());
    }
}
