package com.github.hirnstromwelle.spritebranch.services;
import com.github.hirnstromwelle.spritebranch.models.Hero;
import com.github.hirnstromwelle.spritebranch.repositorys.HeroRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroRepository heroRepository;

    public List<Hero> getAllHeroes() {
        return heroRepository.findAll();
    }

    public Hero saveHero(Hero hero) {
        return heroRepository.save(hero);
    }

    public void deleteHero(String id) {
        Optional<Hero> heroOptional = heroRepository.findById(id);
        heroOptional.ifPresent(heroRepository::delete);
    }
}
