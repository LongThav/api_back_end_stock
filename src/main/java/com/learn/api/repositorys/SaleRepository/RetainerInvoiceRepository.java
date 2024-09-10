package com.learn.api.repositorys.SaleRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.api.models.SaleModel.RetainerInvoiceModel;

@Repository
public interface RetainerInvoiceRepository extends JpaRepository<RetainerInvoiceModel, Long> {
     Optional<RetainerInvoiceModel> findByRetainerInvoiceNumber(String retainerInvoiceNumber);
}
