package com.learn.api.repositorys.SaleRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.api.models.SaleModel.AddressModel;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Long> {
    // You can add custom queries if needed
}
