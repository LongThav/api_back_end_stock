package com.learn.api.repositorys.SaleRepository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.learn.api.models.SaleModel.SaleOrderModel;

@Repository
public interface SaleOrderRepository extends JpaRepository<SaleOrderModel, Long> {
    Optional<SaleOrderModel> findBysalesOrderNumber(String salesOrderNumber);
}
