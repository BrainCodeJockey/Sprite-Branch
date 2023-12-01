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
}
