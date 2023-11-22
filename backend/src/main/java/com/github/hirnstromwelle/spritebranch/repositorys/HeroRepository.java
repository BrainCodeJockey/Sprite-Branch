package com.github.hirnstromwelle.spritebranch.repositorys;
import com.github.hirnstromwelle.spritebranch.models.Hero;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HeroRepository extends MongoRepository<Hero, String> {
}
