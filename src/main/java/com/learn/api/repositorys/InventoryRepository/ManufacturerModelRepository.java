package com.learn.api.repositorys.InventoryRepository;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.api.models.InventoryModel.ManufacturersModel;


public interface ManufacturerModelRepository extends JpaRepository<ManufacturersModel, Long> {
    // List<ManufacturersModel> findAllItems();
}
