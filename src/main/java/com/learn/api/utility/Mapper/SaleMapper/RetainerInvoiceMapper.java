package com.learn.api.utility.Mapper.SaleMapper;

import com.learn.api.dto.SaleDTO.RetainerInvoiceDTO;
import com.learn.api.models.SaleModel.RetainerInvoiceModel;

public class RetainerInvoiceMapper {
    public static RetainerInvoiceDTO toDTO(RetainerInvoiceModel invoice) {
        if (invoice == null) {
            return null;
        }

        RetainerInvoiceDTO dto = new RetainerInvoiceDTO();
        dto.setId(invoice.getId());
        dto.setStatus(invoice.getStatus());
        dto.setCustomerId(invoice.getCustomer().getCustomerId());
        dto.setCustomerName(invoice.getCustomer().getFirstName() + " " + invoice.getCustomer().getLastName());
        dto.setRetainerInvoiceNumber(invoice.getRetainerInvoiceNumber());
        dto.setReferenceNumber(invoice.getReferenceNumber());
        dto.setInvoiceDate(invoice.getInvoiceDate());
        dto.setCurrency(invoice.getCurrency());
        dto.setAmount(invoice.getAmount());
        dto.setTotal(invoice.getTotal());
        return dto;
    }

    // Optionally, add a method to convert DTO back to Entity
    public static RetainerInvoiceModel toEntity(RetainerInvoiceDTO dto) {
        if (dto == null) {
            return null;
        }

        RetainerInvoiceModel invoice = new RetainerInvoiceModel();
        invoice.setId(dto.getId());
        invoice.setRetainerInvoiceNumber(dto.getRetainerInvoiceNumber());
        invoice.setReferenceNumber(dto.getReferenceNumber());
        invoice.setInvoiceDate(dto.getInvoiceDate());
        invoice.setAmount(dto.getAmount());
        invoice.setTotal(dto.getTotal());

        return invoice;
    }
}
