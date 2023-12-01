package com.github.hirnstromwelle.spritebranch.services;
import com.github.hirnstromwelle.spritebranch.models.Item;
import com.github.hirnstromwelle.spritebranch.repositorys.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public void deleteItem(String id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        itemOptional.ifPresent(itemRepository::delete);
    }
}


