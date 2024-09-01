package com.learn.api.repositorys.itemRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learn.api.models.ItemModel.ItemModel;

// @Repository
public interface ItemRepository extends JpaRepository<ItemModel, Long> {
    // Add custom queries if needed
    @Query("SELECT i FROM ItemModel i")
    List<ItemModel> findAllItems();
}
