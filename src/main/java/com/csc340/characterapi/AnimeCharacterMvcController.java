package com.csc340.characterapi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csc340.characterapi.model.AnimeCharacter;
import com.csc340.characterapi.service.AnimeCharacterService;

@Controller
@RequestMapping("/characters")
public class AnimeCharacterMvcController {

    private final AnimeCharacterService animeCharacterService;

    public AnimeCharacterMvcController(AnimeCharacterService animeCharacterService) {
        this.animeCharacterService = animeCharacterService;
    }

    @GetMapping
    public String getAllCharacters(Model model) {
        model.addAttribute("pageTitle", "All Characters");
        model.addAttribute("characterList", animeCharacterService.getAllCharacters());
        return "character-list";
    }

    @GetMapping("/{id}")
    public String getCharacterById(@PathVariable Long id, Model model) {
        model.addAttribute("character", animeCharacterService.getCharacterById(id));
        return "character-details";
    }

    @GetMapping("/createForm")
    public String showCreateForm(Model model) {
        model.addAttribute("character", new AnimeCharacter());
        return "character-create";
    }

    @PostMapping("/create")
    public String createCharacter(AnimeCharacter animeCharacter) {
        AnimeCharacter savedCharacter = animeCharacterService.addCharacter(animeCharacter);
        return "redirect:/characters/" + savedCharacter.getCharacterId();
    }

    @GetMapping("/updateForm/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("character", animeCharacterService.getCharacterById(id));
        return "character-update";
    }

    @PostMapping("/update")
    public String updateCharacter(AnimeCharacter animeCharacter) {
        animeCharacterService.updateCharacter(animeCharacter.getCharacterId(), animeCharacter);
        return "redirect:/characters/" + animeCharacter.getCharacterId();
    }

    @GetMapping("/delete/{id}")
    public String deleteCharacter(@PathVariable Long id) {
        animeCharacterService.deleteCharacter(id);
        return "redirect:/characters";
    }

    @GetMapping("/search")
    public String searchByName(@RequestParam(required = false) String name, Model model) {
        model.addAttribute("pageTitle", "Search Results");
        model.addAttribute("nameQuery", name == null ? "" : name);
        List<AnimeCharacter> characterList = animeCharacterService.searchCharactersByName(name);
        model.addAttribute("characterList", characterList);
        return "character-list";
    }

    @GetMapping("/category")
    public String getCharactersByCategory(@RequestParam(required = false) String category, Model model) {
        return loadCategoryResultsSafely(category, model);
    }

    @GetMapping("/category/{category}")
    public String getCharactersByCategoryPath(@PathVariable String category, Model model) {
        return loadCategoryResultsSafely(category, model);
    }

    private String loadCategoryResultsSafely(String category, Model model) {
        try {
            return loadCategoryResults(category, model);
        } catch (IllegalArgumentException exception) {
            model.addAttribute("pageTitle", "All Characters");
            model.addAttribute("categoryQuery", "");
            model.addAttribute("formError", "Please enter a category.");
            model.addAttribute("characterList", animeCharacterService.getAllCharacters());
            return "character-list";
        }
    }

    private String loadCategoryResults(String category, Model model) {
        if (!StringUtils.hasText(category)) {
            model.addAttribute("pageTitle", "All Characters");
            model.addAttribute("categoryQuery", "");
            model.addAttribute("formError", "Please enter a category.");
            model.addAttribute("characterList", animeCharacterService.getAllCharacters());
            return "character-list";
        }

        String normalizedCategory = category.trim();
        model.addAttribute("pageTitle", "Category Results");
        model.addAttribute("categoryQuery", normalizedCategory);
        List<AnimeCharacter> characterList = animeCharacterService.getCharactersByCategory(normalizedCategory);
        model.addAttribute("characterList", characterList);
        return "character-list";
    }
}
