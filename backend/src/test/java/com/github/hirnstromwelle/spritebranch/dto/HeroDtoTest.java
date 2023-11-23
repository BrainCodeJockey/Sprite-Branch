package com.github.hirnstromwelle.spritebranch.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HeroDtoTest {

    @Test
    void testHeroDto() {
        HeroDto hero = new HeroDto("1", "TestHero", null);
        assertEquals("1", hero.getId());
        assertEquals("TestHero", hero.getName());
        assertNull(hero.getItemIds());

        hero.setName("ChangedName");
        assertEquals("ChangedName", hero.getName());
    }
    @Test
    void testHeroDtoEqualsAndHashCode() {
        HeroDto hero1 = new HeroDto("1", "Hero", null);
        HeroDto hero2 = new HeroDto("1", "Hero", null);
        HeroDto hero3 = new HeroDto("2", "AnotherHero", null);

        assertEquals(hero1, hero2);
        assertNotEquals(hero1, hero3);

        assertEquals(hero1.hashCode(), hero2.hashCode());
        assertNotEquals(hero1.hashCode(), hero3.hashCode());
    }

    @Test
    void testHeroDtoToString() {
        HeroDto hero = new HeroDto("1", "Hero", null);
        String expectedString = "HeroDto(id=1, name=Hero, itemIds=null)";

        assertEquals(expectedString, hero.toString());
    }
}

