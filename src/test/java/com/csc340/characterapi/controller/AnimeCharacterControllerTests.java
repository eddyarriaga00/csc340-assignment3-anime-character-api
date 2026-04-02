package com.csc340.characterapi.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.csc340.characterapi.exception.CharacterNotFoundException;
import com.csc340.characterapi.exception.GlobalExceptionHandler;
import com.csc340.characterapi.model.AnimeCharacter;
import com.csc340.characterapi.service.AnimeCharacterService;

class AnimeCharacterControllerTests {

    private MockMvc mockMvc;
    private AnimeCharacterService animeCharacterService;

    @BeforeEach
    void setUp() {
        animeCharacterService = Mockito.mock(AnimeCharacterService.class);
        AnimeCharacterController animeCharacterController = new AnimeCharacterController(animeCharacterService);
        mockMvc = MockMvcBuilders.standaloneSetup(animeCharacterController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void getAllCharactersReturnsJsonArray() throws Exception {
        when(animeCharacterService.getAllCharacters()).thenReturn(List.of(
                new AnimeCharacter(1L, "Ken Kaneki", "Half-ghoul", "Tokyo Ghoul", "Anti-Hero", "Kagune"),
                new AnimeCharacter(2L, "Naruto Uzumaki", "Ninja hero", "Naruto", "Hero", "Shadow Clone Jutsu")));

        mockMvc.perform(get("/characters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Ken Kaneki"));
    }

    @Test
    void getCharacterByIdReturnsNotFoundForMissingCharacter() throws Exception {
        when(animeCharacterService.getCharacterById(42L)).thenThrow(new CharacterNotFoundException(42L));

        mockMvc.perform(get("/characters/42"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(containsString("42")));
    }

    @Test
    void createCharacterReturnsCreatedStatus() throws Exception {
        AnimeCharacter createdCharacter = new AnimeCharacter(5L, "Tanjiro Kamado",
                "Protects his sister and fights demons.", "Demon Slayer", "Slayer", "Water Breathing");
        when(animeCharacterService.addCharacter(Mockito.any(AnimeCharacter.class))).thenReturn(createdCharacter);

        mockMvc.perform(post("/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "name": "Tanjiro Kamado",
                          "description": "Protects his sister and fights demons.",
                          "series": "Demon Slayer",
                          "category": "Slayer",
                          "signatureAbility": "Water Breathing"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.characterId").value(5))
                .andExpect(jsonPath("$.series").value("Demon Slayer"));
    }

    @Test
    void searchCharactersByNameUsesQueryParameter() throws Exception {
        when(animeCharacterService.searchCharactersByName("nar")).thenReturn(List.of(
                new AnimeCharacter(2L, "Naruto Uzumaki", "Ninja hero", "Naruto", "Hero", "Shadow Clone Jutsu")));

        mockMvc.perform(get("/characters/search").param("name", "nar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Naruto Uzumaki"));
    }
}
