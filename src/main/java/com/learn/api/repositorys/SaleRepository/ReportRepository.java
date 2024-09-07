package com.learn.api.repositorys.SaleRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.api.models.SaleModel.ReportModel;

@Repository
public interface ReportRepository extends JpaRepository<ReportModel, Long> {
    // You can add custom queries if needed
}