package com.learn.api.repositorys.itemRepository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.learn.api.models.ItemModel.TypeWeightModel;

public interface TypeWeightRepository extends JpaRepository<TypeWeightModel, Long> {
    // List<ManufacturersModel> findAllItems();
}
