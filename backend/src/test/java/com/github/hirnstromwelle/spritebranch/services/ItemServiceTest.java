package com.github.hirnstromwelle.spritebranch.services;
import com.github.hirnstromwelle.spritebranch.models.Item;
import com.github.hirnstromwelle.spritebranch.repositorys.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
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

        // WHEN
        when(itemRepository.findAll()).thenReturn(expectedItems);
        List<Item> actualItems = itemService.getAllItems();

        // THEN
        verify(itemRepository).findAll();
        assertEquals(expectedItems, actualItems);
    }

    @Test
    void createItemShouldReturnCreatedItem() {
        // GIVEN
        Item itemToCreate = new Item("1", "Item1", "Type1", false);
        Item createdItem = new Item("1", "Item1", "Type1", false);

        // WHEN
        when(itemRepository.save(any(Item.class))).thenReturn(createdItem);
        Item actualItem = itemService.createItem(itemToCreate);

        // THEN
        verify(itemRepository).save(itemToCreate);
        assertEquals(createdItem, actualItem);
    }
    @Test
    void deleteItemShouldDeleteItem() {
        // GIVEN
        String itemIdToDelete = "1";

        // WHEN
        doNothing().when(itemRepository).deleteById(itemIdToDelete);
        itemService.deleteItem(itemIdToDelete);

        // THEN
        verify(itemRepository).deleteById(itemIdToDelete);
    }

}
