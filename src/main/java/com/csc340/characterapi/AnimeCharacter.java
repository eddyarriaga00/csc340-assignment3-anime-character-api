package com.csc340.characterapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "anime_characters")
public class AnimeCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long characterId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 1200)
    private String description;

    @Column(nullable = false)
    private String series;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String signatureAbility;

    public AnimeCharacter() {
    }

    public AnimeCharacter(Long characterId, String name, String description, String series, String category,
            String signatureAbility) {
        this.characterId = characterId;
        this.name = name;
        this.description = description;
        this.series = series;
        this.category = category;
        this.signatureAbility = signatureAbility;
    }

    public AnimeCharacter(String name, String description, String series, String category, String signatureAbility) {
        this.name = name;
        this.description = description;
        this.series = series;
        this.category = category;
        this.signatureAbility = signatureAbility;
    }

    public Long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSignatureAbility() {
        return signatureAbility;
    }

    public void setSignatureAbility(String signatureAbility) {
        this.signatureAbility = signatureAbility;
    }
}
