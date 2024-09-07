package com.learn.api.repositorys.SaleRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.api.models.SaleModel.OrderDetailModel;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailModel, Long> {
    // You can add custom queries if needed
}
