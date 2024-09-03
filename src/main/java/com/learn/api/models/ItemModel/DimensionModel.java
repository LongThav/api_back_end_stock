package com.learn.api.models.ItemModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_dimensions")

public class DimensionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dimensions_type")
    private String dimensionsType;

    // Default constructor
    public DimensionModel() {
    }

    // Constructor with parameters
    public DimensionModel(String dimensionsType) {
        this.dimensionsType = dimensionsType;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDimensionsType() {
        return dimensionsType;
    }

    public void setDimensionsType(String dimensionsType) {
        this.dimensionsType = dimensionsType;
    }
}
