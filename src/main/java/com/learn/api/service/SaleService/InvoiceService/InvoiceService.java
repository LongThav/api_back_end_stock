package com.learn.api.service.SaleService.InvoiceService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.learn.api.dto.SaleDTO.InvoiceCustomerDTO;
import com.learn.api.dto.SaleDTO.RetainerInvoiceDTO;
import com.learn.api.models.SaleModel.InvoiceModel;
import com.learn.api.models.SaleModel.RetainerInvoiceModel;

public interface InvoiceService {
 
    Page<InvoiceCustomerDTO> getInvoice(Pageable pageable);

    Page<RetainerInvoiceDTO> getRetainerInvoice(Pageable pageable);

    InvoiceModel addInvoice(Long customerId, Long itemId, InvoiceModel invoiceModel);
    
    RetainerInvoiceModel addRetainerInvoice(Long customerId, RetainerInvoiceModel retainerInvoiceModel);
}
