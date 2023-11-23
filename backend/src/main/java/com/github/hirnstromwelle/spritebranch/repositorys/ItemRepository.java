package com.github.hirnstromwelle.spritebranch.repositorys;
import com.github.hirnstromwelle.spritebranch.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
}
