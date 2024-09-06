package com.learn.api.repositorys.InventoryRepository;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.api.models.InventoryModel.BrandModel;


public interface BrandRepository extends JpaRepository<BrandModel, Long> {
    // List<BrandModel> findAllItems();
}
