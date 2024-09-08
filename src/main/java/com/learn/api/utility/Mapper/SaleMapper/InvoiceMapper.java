package com.learn.api.utility.Mapper.SaleMapper;

import java.math.BigDecimal;

import com.learn.api.dto.SaleDTO.InvoiceCustomerDTO;
import com.learn.api.models.SaleModel.InvoiceModel;

public class InvoiceMapper {
    public static InvoiceCustomerDTO mapToInvoiceDTO(InvoiceModel invoice) {
        InvoiceCustomerDTO dto = new InvoiceCustomerDTO();
        dto.setInvocie(invoice.geInvoicetId());
        dto.setDate(invoice.getRetainerInvoiceDate());
        dto.setRetainInvoice(invoice.getRetainerInvoiceNumber());
        dto.setReference(invoice.getReferenc());
        dto.setCustomerId(invoice.getCustomer().getCustomerId()); // You would need to fetch or set the customer name appropriately
        dto.setCustomerName(invoice.getCustomer().getFirstName() + " " + invoice.getCustomer().getLastName());
        dto.setStatus(invoice.getStatus()); // Set the status appropriately based on your logic
        dto.setAmount(invoice.getAmount());
        dto.setBalance(calculateBalance(invoice)); // Implement the balance calculation logic
        return dto;
    }

    private static BigDecimal calculateBalance(InvoiceModel invoice) {
        // Implement the logic to calculate the balance
        return invoice.getTotalAmount().subtract(invoice.getAmount());
    }

}
