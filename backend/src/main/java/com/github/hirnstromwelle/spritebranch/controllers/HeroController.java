package com.github.hirnstromwelle.spritebranch.controllers;
import com.github.hirnstromwelle.spritebranch.dto.HeroDto;
import com.github.hirnstromwelle.spritebranch.models.Hero;
import com.github.hirnstromwelle.spritebranch.services.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.modelmapper.ModelMapper;
import javax.validation.Valid;

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

    @PostMapping
    public HeroDto createHero(@Valid @RequestBody HeroDto heroDTO) {
        Hero hero = modelMapper.map(heroDTO, Hero.class);
        Hero savedHero = heroService.saveHero(hero);
        return modelMapper.map(savedHero, HeroDto.class);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHero(@PathVariable String id) {
        heroService.deleteHero(id);
    }

}
