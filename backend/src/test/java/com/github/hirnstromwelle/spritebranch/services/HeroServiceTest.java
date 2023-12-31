package com.github.hirnstromwelle.spritebranch.services;
import com.github.hirnstromwelle.spritebranch.models.Hero;
import com.github.hirnstromwelle.spritebranch.repositorys.HeroRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class HeroServiceTest {

    @InjectMocks
    private HeroService heroService;

    @Mock
    private HeroRepository heroRepository;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void getAllHeroesShouldReturnListOfHeroes() {
        // GIVEN
        List<Hero> expectedHeroes = List.of(
                new Hero("1", "Hero1", new HashSet<>()),
                new Hero("2", "Hero2", new HashSet<>())
        );

        // WHEN
        when(heroRepository.findAll()).thenReturn(expectedHeroes);
        List<Hero> actualHeroes = heroService.getAllHeroes();

        // THEN
        verify(heroRepository).findAll();
        assertEquals(expectedHeroes, actualHeroes);
    }

    @Test
    void saveHeroShouldReturnSavedHero() {
        // GIVEN
        Hero heroToSave = new Hero("1", "Hero1", new HashSet<>());
        Hero savedHero = new Hero("1", "Hero1", new HashSet<>());

        // WHEN
        when(heroRepository.save(any(Hero.class))).thenReturn(savedHero);
        Hero actualHero = heroService.saveHero(heroToSave);

        // THEN
        verify(heroRepository).save(heroToSave);
        assertEquals(savedHero, actualHero);
    }
    @Test
    void deleteHeroShouldDeleteHero() {
        // GIVEN
        String heroIdToDelete = "1";
        Hero mockHero = new Hero(heroIdToDelete, "Hero1", new HashSet<>());
        when(heroRepository.findById(anyString())).thenReturn(Optional.of(mockHero));

        // WHEN
        heroService.deleteHero(heroIdToDelete);

        // THEN
        verify(heroRepository).findById(heroIdToDelete);
        verify(heroRepository).delete(mockHero);
    }

    @Test
    void deleteHeroShouldDoNothingIfHeroNotFound() {
        // GIVEN
        String heroIdToDelete = "1";
        when(heroRepository.findById(anyString())).thenReturn(Optional.empty());

        // WHEN
        heroService.deleteHero(heroIdToDelete);

        // THEN
        verify(heroRepository).findById(heroIdToDelete);
        verify(heroRepository, never()).delete(any(Hero.class));
    }
    @Test
    void updateHeroShouldUpdateAndReturnUpdatedHero() {
        // GIVEN
        String heroIdToUpdate = "1";
        Hero originalHero = new Hero(heroIdToUpdate, "OriginalHero", new HashSet<>());
        Hero updatedHero = new Hero(heroIdToUpdate, "UpdatedHero", new HashSet<>());

        when(heroRepository.findById(heroIdToUpdate)).thenReturn(Optional.of(originalHero));
        when(heroRepository.save(any(Hero.class))).thenReturn(updatedHero);

        // WHEN
        Hero actualUpdatedHero = heroService.updateHero(heroIdToUpdate, updatedHero);

        // THEN
        verify(heroRepository).findById(heroIdToUpdate);
        verify(heroRepository).save(any(Hero.class)); // Using any(Hero.class) since the hero is modified in the service
        assertEquals(updatedHero.getName(), actualUpdatedHero.getName());
        assertEquals(updatedHero.getItemIds(), actualUpdatedHero.getItemIds());
    }
}
