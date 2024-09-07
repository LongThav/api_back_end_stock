package com.learn.api.repositorys.SaleRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.api.models.SaleModel.RemarkModel;

@Repository
public interface RemarkRepository extends JpaRepository<RemarkModel, Long> {
    // You can add custom queries if needed
}