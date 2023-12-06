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
class SaveGameControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllSaveGamesShouldReturnListOfSaveGames() throws Exception {
        // GIVEN

        // WHEN
        mockMvc.perform(get("/api/savegames"))

                // THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    void createSaveGameShouldReturnCreatedSaveGame() throws Exception {
        // GIVEN
        String saveGameJson = "{\"heroId\":\"HeroId\",\"savedGameState\":\"GameState\"}";

        // WHEN
        mockMvc.perform(post("/api/savegames")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(saveGameJson))

                // THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.heroId").value("HeroId"))
                .andExpect(jsonPath("$.savedGameState").value("GameState"))
                .andExpect(jsonPath("$.saveId").isString());
    }

    @Test
    void deleteSaveGameShouldReturnNoContent() throws Exception {
        // GIVEN
        String saveGameJson = "{\"heroId\":\"HeroToDelete\",\"savedGameState\":\"GameStateToDelete\"}";

        mockMvc.perform(post("/api/savegames")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(saveGameJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.saveId").exists());

        // WHEN
        String createdSaveGameId = mockMvc.perform(get("/api/savegames"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // THEN
        mockMvc.perform(delete("/api/savegames/{saveId}", createdSaveGameId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    @Test
    void updateSaveGameShouldReturnUpdatedSaveGame() throws Exception {
        // GIVEN
        String saveGameJson = "{\"heroId\":\"HeroForUpdate\",\"savedGameState\":\"OriginalState\"}";

        MvcResult createResult = mockMvc.perform(post("/api/savegames")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(saveGameJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.saveId").exists())
                .andReturn();

        String createdSaveGameId = JsonPath.read(createResult.getResponse().getContentAsString(), "$.saveId");

        String updatedSaveGameJson = "{\"heroId\":\"UpdatedHero\",\"savedGameState\":\"UpdatedState\"}";

        // WHEN
        mockMvc.perform(put("/api/savegames/{saveId}", createdSaveGameId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedSaveGameJson))

                // THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.saveId").value(createdSaveGameId))
                .andExpect(jsonPath("$.heroId").value("UpdatedHero"))
                .andExpect(jsonPath("$.savedGameState").value("UpdatedState"));

        // Clean up
        mockMvc.perform(delete("/api/savegames/{saveId}", createdSaveGameId))
                .andExpect(status().isNoContent());
    }
}
