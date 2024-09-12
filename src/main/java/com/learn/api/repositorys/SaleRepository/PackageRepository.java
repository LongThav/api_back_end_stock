package com.learn.api.repositorys.SaleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.api.models.SaleModel.PackageModel;

@Repository
public interface PackageRepository extends JpaRepository<PackageModel, Long> {
    // You can add custom queries if needed
}
