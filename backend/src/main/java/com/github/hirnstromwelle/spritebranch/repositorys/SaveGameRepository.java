package com.github.hirnstromwelle.spritebranch.repositorys;
import com.github.hirnstromwelle.spritebranch.models.SaveGame;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SaveGameRepository extends MongoRepository<SaveGame, String> {
}
