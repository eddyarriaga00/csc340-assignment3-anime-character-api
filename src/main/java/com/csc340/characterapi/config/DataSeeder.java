package com.csc340.characterapi.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.csc340.characterapi.model.AnimeCharacter;
import com.csc340.characterapi.repository.AnimeCharacterRepository;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedCharacters(AnimeCharacterRepository animeCharacterRepository) {
        return args -> {
            if (animeCharacterRepository.count() > 0) {
                return;
            }

            List<AnimeCharacter> starterCharacters = List.of(
                    new AnimeCharacter(
                            "Ken Kaneki",
                            "A traumatized college student who becomes a half-ghoul and fights to protect both humans and ghouls.",
                            "Tokyo Ghoul",
                            "Anti-Hero",
                            "Kagune"),
                    new AnimeCharacter(
                            "Izuku Midoriya",
                            "A determined aspiring hero who inherits One For All and uses strategy, courage, and heart in battle.",
                            "My Hero Academia",
                            "Hero",
                            "One For All"),
                    new AnimeCharacter(
                            "Tanjiro Kamado",
                            "A compassionate swordsman who joins the Demon Slayer Corps to protect his sister and defeat demons.",
                            "Demon Slayer",
                            "Slayer",
                            "Water Breathing"),
                    new AnimeCharacter(
                            "Eren Yeager",
                            "A freedom-driven soldier whose pursuit of justice slowly transforms him into a dangerous anti-hero.",
                            "Attack on Titan",
                            "Anti-Hero",
                            "Attack Titan"),
                    new AnimeCharacter(
                            "Naruto Uzumaki",
                            "A loud but loyal ninja who dreams of becoming Hokage and never gives up on the people he cares about.",
                            "Naruto",
                            "Hero",
                            "Shadow Clone Jutsu"));

            animeCharacterRepository.saveAll(starterCharacters);
        };
    }
}
