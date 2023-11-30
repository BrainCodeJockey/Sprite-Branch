package com.github.hirnstromwelle.spritebranch.repositorys;
import com.github.hirnstromwelle.spritebranch.models.SaveGame;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface SaveGameRepository extends MongoRepository<SaveGame, String> {
    Optional<SaveGame> findBySaveId(String saveId);
}
