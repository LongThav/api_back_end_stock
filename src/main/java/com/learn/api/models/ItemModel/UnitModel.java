package com.learn.api.models.ItemModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_Units") // Assuming the table name in the database is "units"
public class UnitModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assuming UnitID is auto-generated
    private Long UnitID;

    private String Name;

    // Constructors
    public UnitModel() {
    }

    public UnitModel(String name) {
        this.Name = name;
    }

    // Getters and Setters
    public Long getUnitID() {
        return UnitID;
    }

    public void setUnitID(Long unitID) {
        this.UnitID = unitID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    // Optional: toString, equals, and hashCode methods
    @Override
    public String toString() {
        return "UnitModel{" +
                "UnitID=" + UnitID +
                ", Name='" + Name + '\'' +
                '}';
    }
}