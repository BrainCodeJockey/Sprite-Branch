package com.github.hirnstromwelle.spritebranch.controllers;

import com.github.hirnstromwelle.spritebranch.dto.HeroDto;
import com.github.hirnstromwelle.spritebranch.models.Hero;
import com.github.hirnstromwelle.spritebranch.services.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/heroes")
@RequiredArgsConstructor
public class HeroController {

    private final HeroService heroService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<HeroDto> getAllHeroes() {
        List<Hero> heroes = heroService.getAllHeroes();
        return heroes.stream()
                .map(hero -> modelMapper.map(hero, HeroDto.class))
                .toList();
    }

    @GetMapping("/{id}")
    public HeroDto getHeroById(@PathVariable String id) {
        Hero hero = heroService.getHeroById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hero not found"));
        return modelMapper.map(hero, HeroDto.class);
    }

    @PostMapping
    public HeroDto createHero(@Valid @RequestBody HeroDto heroDTO) {
        Hero hero = modelMapper.map(heroDTO, Hero.class);
        Hero savedHero = heroService.saveHero(hero);
        return modelMapper.map(savedHero, HeroDto.class);
    }

    @PutMapping("/{id}")
    public HeroDto updateHero(@PathVariable String id, @Valid @RequestBody HeroDto heroDTO) {
        Hero updatedHero = heroService.updateHero(id, modelMapper.map(heroDTO, Hero.class));
        return modelMapper.map(updatedHero, HeroDto.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHero(@PathVariable String id) {
        heroService.deleteHero(id);
    }
}
