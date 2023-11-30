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
    @Test
    void testHeroEqualsAndHashCode() {
        Hero hero1 = new Hero("1", "Hero", new HashSet<>());
        Hero hero2 = new Hero("1", "Hero", new HashSet<>());
        Hero hero3 = new Hero("2", "AnotherHero", new HashSet<>());

        assertEquals(hero1, hero2);
        assertNotEquals(hero1, hero3);

        assertEquals(hero1.hashCode(), hero2.hashCode());
        assertNotEquals(hero1.hashCode(), hero3.hashCode());
    }

    @Test
    void testHeroToString() {
        Hero hero = new Hero("1", "Hero", new HashSet<>());
        assertNotNull(hero.toString());
    }
}
