package com.learn.api.repositorys.InventoryRepository;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.api.models.InventoryModel.PreferVendorModel;

public interface PreferVendorRepository extends JpaRepository<PreferVendorModel, Long> {
    // List<PreferVendorModel> findAllItems();
}
