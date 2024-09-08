package com.learn.api.service.SaleService.InvoiceService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.learn.api.dto.SaleDTO.InvoiceCustomerDTO;
import com.learn.api.models.SaleModel.InvoiceModel;

public interface InvoiceService {
    
    Page<InvoiceCustomerDTO> getInvoice(Pageable pageable);

    InvoiceModel addInvoice(Long customerId, InvoiceModel invoiceModel);
}
