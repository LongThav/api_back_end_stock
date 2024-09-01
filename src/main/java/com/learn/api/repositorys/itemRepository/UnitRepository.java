package com.learn.api.repositorys.itemRepository;

import java.util.List;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learn.api.models.ItemModel.UnitModel;

public interface UnitRepository extends JpaRepository<UnitModel, Long> {
    @Query("SELECT i FROM UnitModel i")
    List<UnitModel> findAllItems();
}
