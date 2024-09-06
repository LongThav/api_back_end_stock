package com.learn.api.repositorys.InventoryRepository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.api.models.InventoryModel.TypeWeightModel;

public interface TypeWeightRepository extends JpaRepository<TypeWeightModel, Long> {
    // List<ManufacturersModel> findAllItems();
}
