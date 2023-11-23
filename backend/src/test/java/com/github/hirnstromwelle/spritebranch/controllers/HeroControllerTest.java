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
class HeroControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllHeroesShouldReturnListOfHeroes() throws Exception {
        mockMvc.perform(get("/api/heroes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    void createHeroShouldReturnCreatedHero() throws Exception {
        String heroJson = "{\"name\":\"TestHero\",\"itemIds\":[]}";

        mockMvc.perform(post("/api/heroes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(heroJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("TestHero"));
    }
}

