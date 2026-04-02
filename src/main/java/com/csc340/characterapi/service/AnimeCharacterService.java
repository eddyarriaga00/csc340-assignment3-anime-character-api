package com.csc340.characterapi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.csc340.characterapi.exception.CharacterNotFoundException;
import com.csc340.characterapi.model.AnimeCharacter;
import com.csc340.characterapi.repository.AnimeCharacterRepository;

@Service
public class AnimeCharacterService {

    private final AnimeCharacterRepository animeCharacterRepository;

    public AnimeCharacterService(AnimeCharacterRepository animeCharacterRepository) {
        this.animeCharacterRepository = animeCharacterRepository;
    }

    public List<AnimeCharacter> getAllCharacters() {
        return animeCharacterRepository.findAll();
    }

    public AnimeCharacter getCharacterById(Long id) {
        return animeCharacterRepository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(id));
    }

    public AnimeCharacter addCharacter(AnimeCharacter animeCharacter) {
        validateCharacter(animeCharacter);
        animeCharacter.setCharacterId(null);
        normalizeCharacter(animeCharacter);
        return animeCharacterRepository.save(animeCharacter);
    }

    public AnimeCharacter updateCharacter(Long id, AnimeCharacter updatedCharacter) {
        validateCharacter(updatedCharacter);
        normalizeCharacter(updatedCharacter);

        AnimeCharacter existingCharacter = getCharacterById(id);
        existingCharacter.setName(updatedCharacter.getName());
        existingCharacter.setDescription(updatedCharacter.getDescription());
        existingCharacter.setSeries(updatedCharacter.getSeries());
        existingCharacter.setCategory(updatedCharacter.getCategory());
        existingCharacter.setSignatureAbility(updatedCharacter.getSignatureAbility());
        return animeCharacterRepository.save(existingCharacter);
    }

    public void deleteCharacter(Long id) {
        AnimeCharacter existingCharacter = getCharacterById(id);
        animeCharacterRepository.delete(existingCharacter);
    }

    public List<AnimeCharacter> getCharactersByCategory(String category) {
        if (!StringUtils.hasText(category)) {
            throw new IllegalArgumentException("Category is required.");
        }
        return animeCharacterRepository.findByCategoryIgnoreCase(category.trim());
    }

    public List<AnimeCharacter> searchCharactersByName(String name) {
        if (!StringUtils.hasText(name)) {
            return getAllCharacters();
        }
        return animeCharacterRepository.searchByNameContaining(name.trim());
    }

    private void validateCharacter(AnimeCharacter animeCharacter) {
        if (animeCharacter == null) {
            throw new IllegalArgumentException("Request body is required.");
        }
        if (!StringUtils.hasText(animeCharacter.getName())) {
            throw new IllegalArgumentException("Character name is required.");
        }
        if (!StringUtils.hasText(animeCharacter.getDescription())) {
            throw new IllegalArgumentException("Character description is required.");
        }
        if (!StringUtils.hasText(animeCharacter.getSeries())) {
            throw new IllegalArgumentException("Character series is required.");
        }
        if (!StringUtils.hasText(animeCharacter.getCategory())) {
            throw new IllegalArgumentException("Character category is required.");
        }
        if (!StringUtils.hasText(animeCharacter.getSignatureAbility())) {
            throw new IllegalArgumentException("Character signature ability is required.");
        }
    }

    private void normalizeCharacter(AnimeCharacter animeCharacter) {
        animeCharacter.setName(animeCharacter.getName().trim());
        animeCharacter.setDescription(animeCharacter.getDescription().trim());
        animeCharacter.setSeries(animeCharacter.getSeries().trim());
        animeCharacter.setCategory(animeCharacter.getCategory().trim());
        animeCharacter.setSignatureAbility(animeCharacter.getSignatureAbility().trim());
    }
}
