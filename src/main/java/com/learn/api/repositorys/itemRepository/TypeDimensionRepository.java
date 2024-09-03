package com.learn.api.repositorys.itemRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.learn.api.models.ItemModel.DimensionModel;

public interface TypeDimensionRepository extends JpaRepository<DimensionModel, Long> {
    // List<ManufacturersModel> findAllItems();
}
