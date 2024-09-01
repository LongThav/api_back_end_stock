package com.learn.api.models.ItemModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_Manufacturers")
public class ManufacturersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tbl_ManufacturersID;

    private String Name;

    // Constructors
    public ManufacturersModel() {
    }

    public ManufacturersModel(String name) {
        this.Name = name;
    }

    // Getters and Setters
    public Long getTbl_ManufacturersID() {
        return tbl_ManufacturersID;
    }

    public void setTbl_ManufacturersID(Long tbl_ManufacturersID) {
        this.tbl_ManufacturersID = tbl_ManufacturersID;
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
        return "ManufacturersModel{" +
                "tbl_ManufacturersID=" + tbl_ManufacturersID +
                ", Name='" + Name + '\'' +
                '}';
    }
}