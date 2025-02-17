package com.learn.api.repositorys.SaleRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.api.models.SaleModel.InvoiceModel;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceModel, Long> {
    // You can add custom queries if needed
    Optional<InvoiceModel> findByRetainerInvoiceNumber(String retainerInvoiceNumber);
}
