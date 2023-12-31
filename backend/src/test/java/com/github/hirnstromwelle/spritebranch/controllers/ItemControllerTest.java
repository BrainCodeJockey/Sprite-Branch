package com.github.hirnstromwelle.spritebranch.controllers;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllItemsShouldReturnListOfItems() throws Exception {
        // GIVEN

        // WHEN
        mockMvc.perform(get("/api/items"))

                // THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    void createItemShouldReturnCreatedItem() throws Exception {
        // GIVEN
        String itemJson = "{\"name\":\"TestItem\",\"type\":\"ItemType\",\"found\":false}";

        // WHEN
        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(itemJson))

                // THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("TestItem"))
                .andExpect(jsonPath("$.type").value("ItemType"))
                .andExpect(jsonPath("$.found").value(false));
    }

    @Test
    void deleteItemShouldReturnNoContent() throws Exception {
        // GIVEN
        String itemJson = "{\"name\":\"ItemToDelete\",\"type\":\"ItemType\",\"found\":false}";

        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(itemJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists());

        // WHEN
        String createdItemId = mockMvc.perform(get("/api/items"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // THEN
        mockMvc.perform(delete("/api/items/{id}", createdItemId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    @Test
    void updateItemShouldReturnUpdatedItem() throws Exception {
        // GIVEN
        String itemJson = "{\"name\":\"ItemToUpdate\",\"type\":\"ItemType\",\"found\":false}";

        MvcResult result = mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(itemJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andReturn();

        String createdItemId = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

        String updatedItemJson = "{\"name\":\"UpdatedItem\",\"type\":\"UpdatedType\",\"found\":true}";

        // WHEN
        mockMvc.perform(put("/api/items/{id}", createdItemId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedItemJson))

                // THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(createdItemId))
                .andExpect(jsonPath("$.name").value("UpdatedItem"))
                .andExpect(jsonPath("$.type").value("UpdatedType"))
                .andExpect(jsonPath("$.found").value(true));

        // Clean up
        mockMvc.perform(delete("/api/items/{id}", createdItemId))
                .andExpect(status().isNoContent());
    }
}
