package com.learn.api.repositorys.SaleRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.api.models.SaleModel.ContactPersonModel;

@Repository
public interface ContactPersonRepository extends JpaRepository<ContactPersonModel, Long> {
    // You can add custom queries if needed
}