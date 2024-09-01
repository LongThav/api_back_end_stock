package com.learn.api.repositorys.itemRepository;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.api.models.ItemModel.PreferVendorModel;

public interface PreferVendorRepository extends JpaRepository<PreferVendorModel, Long> {
    // List<PreferVendorModel> findAllItems();
}
