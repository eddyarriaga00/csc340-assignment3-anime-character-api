package com.csc340.characterapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.csc340.characterapi.exception.CharacterNotFoundException;
import com.csc340.characterapi.model.AnimeCharacter;
import com.csc340.characterapi.repository.AnimeCharacterRepository;

@ExtendWith(MockitoExtension.class)
class AnimeCharacterServiceTests {

    @Mock
    private AnimeCharacterRepository animeCharacterRepository;

    @InjectMocks
    private AnimeCharacterService animeCharacterService;

    @Test
    void getCharacterByIdReturnsCharacterWhenFound() {
        AnimeCharacter animeCharacter = new AnimeCharacter(1L, "Naruto Uzumaki", "Hidden Leaf ninja", "Naruto", "Hero",
                "Shadow Clone Jutsu");
        when(animeCharacterRepository.findById(1L)).thenReturn(Optional.of(animeCharacter));

        AnimeCharacter result = animeCharacterService.getCharacterById(1L);

        assertEquals("Naruto Uzumaki", result.getName());
        assertEquals("Hero", result.getCategory());
    }

    @Test
    void getCharacterByIdThrowsWhenMissing() {
        when(animeCharacterRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(CharacterNotFoundException.class, () -> animeCharacterService.getCharacterById(99L));
    }

    @Test
    void searchCharactersByNameReturnsAllCharactersWhenNameMissing() {
        when(animeCharacterRepository.findAll()).thenReturn(List.of(
                new AnimeCharacter(1L, "Ken Kaneki", "Half-ghoul", "Tokyo Ghoul", "Anti-Hero", "Kagune"),
                new AnimeCharacter(2L, "Tanjiro Kamado", "Demon slayer", "Demon Slayer", "Slayer",
                        "Water Breathing")));

        List<AnimeCharacter> results = animeCharacterService.searchCharactersByName(" ");

        assertEquals(2, results.size());
        verify(animeCharacterRepository).findAll();
    }

    @Test
    void updateCharacterCopiesNewValuesOntoExistingCharacter() {
        AnimeCharacter existing = new AnimeCharacter(3L, "Eren Yeager", "Old description", "Attack on Titan",
                "Anti-Hero", "Attack Titan");
        AnimeCharacter updated = new AnimeCharacter("Eren Yeager",
                "A soldier whose obsession with freedom changes the world.",
                "Attack on Titan", "Anti-Hero", "Founding Titan");

        when(animeCharacterRepository.findById(3L)).thenReturn(Optional.of(existing));
        when(animeCharacterRepository.save(existing)).thenReturn(existing);

        AnimeCharacter result = animeCharacterService.updateCharacter(3L, updated);

        assertEquals("A soldier whose obsession with freedom changes the world.", result.getDescription());
        assertEquals("Founding Titan", result.getSignatureAbility());
    }
}
