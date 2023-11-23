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
}
