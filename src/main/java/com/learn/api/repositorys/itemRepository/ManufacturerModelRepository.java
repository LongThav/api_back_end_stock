package com.learn.api.repositorys.itemRepository;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.api.models.ItemModel.ManufacturersModel;


public interface ManufacturerModelRepository extends JpaRepository<ManufacturersModel, Long> {
    // List<ManufacturersModel> findAllItems();
}
