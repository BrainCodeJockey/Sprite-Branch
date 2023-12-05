package com.github.hirnstromwelle.spritebranch.services;

import com.github.hirnstromwelle.spritebranch.models.Item;
import com.github.hirnstromwelle.spritebranch.repositorys.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;

    @Test
    void getAllItemsShouldReturnListOfItems() {
        // GIVEN
        List<Item> expectedItems = List.of(
                new Item("1", "Item1", "Type1", false),
                new Item("2", "Item2", "Type2", true)
        );
        when(itemRepository.findAll()).thenReturn(expectedItems);

        // WHEN
        List<Item> actualItems = itemService.getAllItems();

        // THEN
        verify(itemRepository).findAll();
        assertEquals(expectedItems, actualItems);
    }

    @Test
    void getAllItemsShouldReturnEmptyListIfNoItems() {
        // GIVEN
        when(itemRepository.findAll()).thenReturn(Collections.emptyList());

        // WHEN
        List<Item> actualItems = itemService.getAllItems();

        // THEN
        verify(itemRepository).findAll();
        assertTrue(actualItems.isEmpty());
    }

    @Test
    void createItemShouldReturnCreatedItem() {
        // GIVEN
        Item itemToCreate = new Item("1", "Item1", "Type1", false);
        Item createdItem = new Item("1", "Item1", "Type1", false);
        when(itemRepository.save(any(Item.class))).thenReturn(createdItem);

        // WHEN
        Item actualItem = itemService.createItem(itemToCreate);

        // THEN
        verify(itemRepository).save(itemToCreate);
        assertEquals(createdItem, actualItem);
    }

    @Test
    void deleteItemShouldDeleteItem() {
        // GIVEN
        String itemIdToDelete = "1";
        Item mockItem = new Item(itemIdToDelete, "Item1", "Type1", false);
        when(itemRepository.findById(itemIdToDelete)).thenReturn(Optional.of(mockItem));

        // WHEN
        itemService.deleteItem(itemIdToDelete);

        // THEN
        verify(itemRepository).findById(itemIdToDelete);
        verify(itemRepository).delete(mockItem);
    }
    @Test
    void deleteItemShouldDoNothingIfItemNotFound() {
        // GIVEN
        String itemIdToDelete = "1";
        when(itemRepository.findById(itemIdToDelete)).thenReturn(Optional.empty());

        // WHEN
        itemService.deleteItem(itemIdToDelete);

        // THEN
        verify(itemRepository).findById(itemIdToDelete);
        verify(itemRepository, never()).delete(any(Item.class));
    }
    @Test
    void updateItemShouldUpdateAndReturnUpdatedItem() {
        // GIVEN
        String itemIdToUpdate = "1";
        Item originalItem = new Item(itemIdToUpdate, "OriginalItem", "Type1", false);
        Item updatedItem = new Item(itemIdToUpdate, "UpdatedItem", "Type2", true);

        when(itemRepository.findById(itemIdToUpdate)).thenReturn(Optional.of(originalItem));
        when(itemRepository.save(any(Item.class))).thenReturn(updatedItem);

        // WHEN
        Item actualUpdatedItem = itemService.updateItem(itemIdToUpdate, updatedItem);

        // THEN
        verify(itemRepository).findById(itemIdToUpdate);
        verify(itemRepository).save(any(Item.class));
        assertEquals(updatedItem.getName(), actualUpdatedItem.getName());
        assertEquals(updatedItem.getType(), actualUpdatedItem.getType());
        assertEquals(updatedItem.isFound(), actualUpdatedItem.isFound());
    }
}
