package com.csc340.characterapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.csc340.characterapi.model.AnimeCharacter;
import com.csc340.characterapi.service.AnimeCharacterService;

@RestController
@RequestMapping("/characters")
public class AnimeCharacterController {

    private final AnimeCharacterService animeCharacterService;

    public AnimeCharacterController(AnimeCharacterService animeCharacterService) {
        this.animeCharacterService = animeCharacterService;
    }

    @GetMapping
    public List<AnimeCharacter> getAllCharacters() {
        return animeCharacterService.getAllCharacters();
    }

    @GetMapping("/{id}")
    public AnimeCharacter getCharacterById(@PathVariable Long id) {
        return animeCharacterService.getCharacterById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimeCharacter addCharacter(@RequestBody AnimeCharacter animeCharacter) {
        return animeCharacterService.addCharacter(animeCharacter);
    }

    @PutMapping("/{id}")
    public AnimeCharacter updateCharacter(@PathVariable Long id, @RequestBody AnimeCharacter updatedCharacter) {
        return animeCharacterService.updateCharacter(id, updatedCharacter);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCharacter(@PathVariable Long id) {
        animeCharacterService.deleteCharacter(id);
    }

    @GetMapping("/category/{category}")
    public List<AnimeCharacter> getCharactersByCategory(@PathVariable String category) {
        return animeCharacterService.getCharactersByCategory(category);
    }

    @GetMapping("/search")
    public List<AnimeCharacter> searchCharactersByName(@RequestParam(required = false) String name) {
        return animeCharacterService.searchCharactersByName(name);
    }
}
