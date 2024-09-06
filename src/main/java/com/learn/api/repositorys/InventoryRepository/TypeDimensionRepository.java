package com.learn.api.repositorys.InventoryRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.api.models.InventoryModel.DimensionModel;

public interface TypeDimensionRepository extends JpaRepository<DimensionModel, Long> {
    // List<ManufacturersModel> findAllItems();
}
