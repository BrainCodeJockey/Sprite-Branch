package com.github.hirnstromwelle.spritebranch.controllers;
import com.github.hirnstromwelle.spritebranch.dto.ItemDto;
import com.github.hirnstromwelle.spritebranch.models.Item;
import com.github.hirnstromwelle.spritebranch.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<ItemDto> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return items.stream()
                .map(item -> modelMapper.map(item, ItemDto.class))
                .toList();
    }

    @PostMapping
    public ItemDto createItem(@Valid @RequestBody ItemDto itemDTO) {
        Item item = modelMapper.map(itemDTO, Item.class);
        Item createdItem = itemService.createItem(item);
        return modelMapper.map(createdItem, ItemDto.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable String id) {
        itemService.deleteItem(id);
    }
}
