package com.github.hirnstromwelle.spritebranch.models;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    @Test
    void testHeroManipulations() {
        HashSet<String> itemIds = new HashSet<>();
        Hero hero = new Hero("1", "TestHero", itemIds);

        assertEquals("1", hero.getId());
        assertEquals("TestHero", hero.getName());
        assertTrue(hero.getItemIds().isEmpty());

        hero.addItem("Item1");
        assertTrue(hero.hasItem("Item1"));
        assertEquals(1, hero.getItemIds().size());

        hero.removeItem("Item1");
        assertFalse(hero.hasItem("Item1"));
        assertTrue(hero.getItemIds().isEmpty());
    }
}
