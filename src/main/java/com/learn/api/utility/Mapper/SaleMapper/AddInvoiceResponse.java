package com.learn.api.utility.Mapper.SaleMapper;

import com.learn.api.dto.SaleDTO.AddInvoiceResponseDTO;
import com.learn.api.models.SaleModel.InvoiceModel;

public class AddInvoiceResponse {
    // Convert InvoiceModel to InvoiceResponseDTO
    public static AddInvoiceResponseDTO toDTO(InvoiceModel invoice) {
        AddInvoiceResponseDTO dto = new AddInvoiceResponseDTO();
        dto.setCurrency(invoice.getCurrency());
        dto.setBillingAddress(invoice.getBillingAddress());
        dto.setBillingCity(invoice.getBillingCity());
        dto.setBillingState(invoice.getBillingState());
        dto.setBillingZipCode(invoice.getBillingZipCode());
        dto.setBillingCountry(invoice.getBillingCountry());
        dto.setBillingPhone(invoice.getBillingPhone());
        dto.setBillingFax(invoice.getBillingFax());
        dto.setRetainerInvoiceNumber(invoice.getRetainerInvoiceNumber());
        dto.setReferenceNumber(invoice.getReferenc());
        dto.setRetainerInvoiceDate(invoice.getRetainerInvoiceDate());
        dto.setDescription(invoice.getDescription());
        dto.setTax(invoice.getTax());
        dto.setAmount(invoice.getAmount());
        dto.setCustomerNotes(invoice.getCustomerNotes());
        dto.setSubTotal(invoice.getSubTotal());
        dto.setRoundOff(invoice.getRoundOff());
        dto.setTotalAmount(invoice.getTotalAmount());
        dto.setTermsConditions(invoice.getTermsConditions());
        return dto;
    }

    // Convert InvoiceResponseDTO to InvoiceModel
    public static InvoiceModel fromDTO(AddInvoiceResponseDTO dto) {
        InvoiceModel invoice = new InvoiceModel();
        invoice.setCurrency(dto.getCurrency());
        invoice.setBillingAddress(dto.getBillingAddress());
        invoice.setBillingCity(dto.getBillingCity());
        invoice.setBillingState(dto.getBillingState());
        invoice.setBillingZipCode(dto.getBillingZipCode());
        invoice.setBillingCountry(dto.getBillingCountry());
        invoice.setBillingPhone(dto.getBillingPhone());
        invoice.setBillingFax(dto.getBillingFax());
        invoice.setRetainerInvoiceNumber(dto.getRetainerInvoiceNumber());
        invoice.setReferenc(dto.getReferenceNumber());
        invoice.setRetainerInvoiceDate(dto.getRetainerInvoiceDate());
        invoice.setDescription(dto.getDescription());
        invoice.setTax(dto.getTax());
        invoice.setAmount(dto.getAmount());
        invoice.setCustomerNotes(dto.getCustomerNotes());
        invoice.setSubTotal(dto.getSubTotal());
        invoice.setRoundOff(dto.getRoundOff());
        invoice.setTotalAmount(dto.getTotalAmount());
        invoice.setTermsConditions(dto.getTermsConditions());
        return invoice;
    }
}
