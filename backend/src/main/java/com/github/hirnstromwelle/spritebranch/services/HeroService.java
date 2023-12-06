package com.github.hirnstromwelle.spritebranch.services;
import com.github.hirnstromwelle.spritebranch.models.Hero;
import com.github.hirnstromwelle.spritebranch.repositorys.HeroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroRepository heroRepository;

    public List<Hero> getAllHeroes() {
        return heroRepository.findAll();
    }

    public Optional<Hero> getHeroById(String id) {
        return heroRepository.findById(id);
    }

    public Hero saveHero(Hero hero) {
        return heroRepository.save(hero);
    }

    @Transactional
    public Hero updateHero(String id, Hero updatedHero) {
        return heroRepository.findById(id)
                .map(hero -> {
                    hero.setName(updatedHero.getName());
                    hero.setItemIds(updatedHero.getItemIds());
                    return heroRepository.save(hero);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hero not found"));
    }

    public void deleteHero(String id) {
        Optional<Hero> heroOptional = heroRepository.findById(id);
        heroOptional.ifPresent(heroRepository::delete);
    }
}
