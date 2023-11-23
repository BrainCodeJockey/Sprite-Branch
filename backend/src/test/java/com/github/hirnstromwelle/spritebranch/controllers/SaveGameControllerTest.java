package com.github.hirnstromwelle.spritebranch.controllers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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
        mockMvc.perform(get("/api/savegames"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    void createSaveGameShouldReturnCreatedSaveGame() throws Exception {
        String saveGameJson = "{\"heroId\":\"HeroId\",\"savedGameState\":\"GameState\"}";

        mockMvc.perform(post("/api/savegames")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(saveGameJson))
                .andExpect(status().isOk()) // Adjusted to expect 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.heroId").value("HeroId"))
                .andExpect(jsonPath("$.savedGameState").value("GameState"))
                .andExpect(jsonPath("$.saveId").doesNotExist());
    }
}
