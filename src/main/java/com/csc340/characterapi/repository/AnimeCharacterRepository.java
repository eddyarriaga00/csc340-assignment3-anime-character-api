package com.csc340.characterapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.csc340.characterapi.model.AnimeCharacter;

@Repository
public interface AnimeCharacterRepository extends JpaRepository<AnimeCharacter, Long> {

    @Query(value = """
            SELECT *
            FROM anime_characters
            WHERE LOWER(category) = LOWER(?1)
            ORDER BY name
            """, nativeQuery = true)
    List<AnimeCharacter> findByCategoryIgnoreCase(String category);

    @Query(value = """
            SELECT *
            FROM anime_characters
            WHERE LOWER(name) LIKE LOWER(CONCAT('%', ?1, '%'))
            ORDER BY name
            """, nativeQuery = true)
    List<AnimeCharacter> searchByNameContaining(String name);
}
