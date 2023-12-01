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
    @Test
    void testItemEqualsAndHashCode() {
        Item item1 = new Item("1", "Sword", "Weapon", false);
        Item item2 = new Item("1", "Sword", "Weapon", false);
        Item item3 = new Item("2", "Axe", "Weapon", false);

        assertEquals(item1, item2);
        assertNotEquals(item1, item3);

        assertEquals(item1.hashCode(), item2.hashCode());
        assertNotEquals(item1.hashCode(), item3.hashCode());
    }

    @Test
    void testItemToString() {
        Item item = new Item("1", "Sword", "Weapon", false);
        assertNotNull(item.toString());
    }
}
