package com.github.hirnstromwelle.spritebranch.services;
import com.github.hirnstromwelle.spritebranch.models.Item;
import com.github.hirnstromwelle.spritebranch.repositorys.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
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

    @Transactional
    public Item updateItem(String id, Item updatedItem) {
        return itemRepository.findById(id)
                .map(item -> {
                    item.setName(updatedItem.getName());
                    item.setType(updatedItem.getType());
                    item.setFound(updatedItem.isFound());
                    return itemRepository.save(item);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"));
    }

    public void deleteItem(String id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        itemOptional.ifPresent(itemRepository::delete);
    }
}
