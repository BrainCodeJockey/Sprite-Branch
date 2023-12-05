package com.github.hirnstromwelle.spritebranch.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hirnstromwelle.spritebranch.dto.HeroDto;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;


@SpringBootTest
@AutoConfigureMockMvc
class HeroControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenExistingHeroes_whenGetAllHeroes_thenStatusOkAndListOfHeroes() throws Exception {
        // Given

        // When
        mockMvc.perform(get("/api/heroes"))

                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    void givenNewHeroData_whenCreateHero_thenStatusOkAndCreatedHero() throws Exception {
        // Given
        String heroJson = "{\"name\":\"TestHero\",\"itemIds\":[]}";

        // When
        mockMvc.perform(post("/api/heroes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(heroJson))

                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("TestHero"));
    }

    @Test
    void givenExistingHero_whenDeleteHero_thenStatusNoContent() throws Exception {
        // Given
        HeroDto testHeroDto = new HeroDto();
        testHeroDto.setName("TestHeroToDelete");
        testHeroDto.setItemIds(new HashSet<>());

        String heroJson = objectMapper.writeValueAsString(testHeroDto);

        MvcResult result = mockMvc.perform(post("/api/heroes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(heroJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andReturn();

        String createdHeroId = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

        // When
        mockMvc.perform(delete("/api/heroes/{id}", createdHeroId)
                        .contentType(MediaType.APPLICATION_JSON))

                // Then
                .andExpect(status().isNoContent());
    }
    @Test
    void givenExistingHeroId_whenGetHeroById_thenStatusOkAndHeroDetails() throws Exception {
        // Given
        HeroDto newHero = new HeroDto();
        newHero.setName("TemporaryTestHero");
        newHero.setItemIds(new HashSet<>());
        String newHeroJson = objectMapper.writeValueAsString(newHero);

        MvcResult result = mockMvc.perform(post("/api/heroes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newHeroJson))
                .andExpect(status().isOk())
                .andReturn();

        String heroId = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

        // When
        mockMvc.perform(get("/api/heroes/{id}", heroId))

                // Then: Überprüfung der Details
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(heroId))
                .andExpect(jsonPath("$.name").value("TemporaryTestHero"))
                .andExpect(jsonPath("$.itemIds").isArray());

        mockMvc.perform(delete("/api/heroes/{id}", heroId))
                .andExpect(status().isNoContent());
    }


    @Test
    void givenExistingHero_whenUpdateHero_thenStatusOkAndUpdatedHeroDetails() throws Exception {
        // Given
        HeroDto newHero = new HeroDto();
        newHero.setName("HeroToUpdate");
        newHero.setItemIds(new HashSet<>());
        String newHeroJson = objectMapper.writeValueAsString(newHero);

        MvcResult result = mockMvc.perform(post("/api/heroes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newHeroJson))
                .andExpect(status().isOk())
                .andReturn();

        String heroId = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

        // Given
        HeroDto updatedHero = new HeroDto();
        updatedHero.setName("UpdatedHeroName");
        updatedHero.setItemIds(new HashSet<>(Arrays.asList("item1", "item2")));
        String updatedHeroJson = objectMapper.writeValueAsString(updatedHero);

        // When
        mockMvc.perform(put("/api/heroes/{id}", heroId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedHeroJson))

                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(heroId))
                .andExpect(jsonPath("$.name").value("UpdatedHeroName"))
                .andExpect(jsonPath("$.itemIds", hasSize(2)));

        // Clean up
        mockMvc.perform(delete("/api/heroes/{id}", heroId))
                .andExpect(status().isNoContent());
    }
}
