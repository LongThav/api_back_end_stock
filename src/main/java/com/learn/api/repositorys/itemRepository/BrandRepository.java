package com.learn.api.repositorys.itemRepository;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.api.models.ItemModel.BrandModel;


public interface BrandRepository extends JpaRepository<BrandModel, Long> {
    // List<BrandModel> findAllItems();
}
