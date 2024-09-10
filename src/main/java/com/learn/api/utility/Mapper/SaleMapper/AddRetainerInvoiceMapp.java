package com.learn.api.utility.Mapper.SaleMapper;

import com.learn.api.dto.SaleDTO.AddRetainerInvoiceDTO;
import com.learn.api.models.SaleModel.RetainerInvoiceModel;

public class AddRetainerInvoiceMapp {
    // Convert RetainerInvoice entity to AddRetainerInvoiceDTO
    public static AddRetainerInvoiceDTO toDTO(RetainerInvoiceModel entity) {
        if (entity == null) {
            return null;
        }

        AddRetainerInvoiceDTO dto = new AddRetainerInvoiceDTO();
        dto.setId(entity.getId());
        dto.setCustomerId(entity.getCustomer().getCustomerId()); // Assuming CustomerModel is mapped to the entity                                                    // directly
        dto.setRetainerInvoiceNumber(entity.getRetainerInvoiceNumber());
        dto.setReferenceNumber(entity.getReferenceNumber());
        dto.setInvoiceDate(entity.getInvoiceDate());
        dto.setDescription(entity.getDescription());
        dto.setTax(entity.getTax());
        dto.setAmount(entity.getAmount());
        dto.setCustomerNotes(entity.getCustomerNotes());
        dto.setSubTotal(entity.getSubTotal());
        dto.setRoundOff(entity.getRoundOff());
        dto.setTotal(entity.getTotal());
        dto.setTermsConditions(entity.getTermsConditions());
        dto.setCurrency(entity.getCurrency());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setStatus(entity.getStatus());

        return dto;
    }

    // Convert AddRetainerInvoiceDTO to RetainerInvoice entity
    public static RetainerInvoiceModel toEntity(AddRetainerInvoiceDTO dto) {
        if (dto == null) {
            return null;
        }

        RetainerInvoiceModel entity = new RetainerInvoiceModel();
        entity.setId(dto.getId());
        dto.setCustomerId(entity.getCustomer().getCustomerId());
        ; // Assuming CustomerModel is mapped to the entity directly
        entity.setRetainerInvoiceNumber(dto.getRetainerInvoiceNumber());
        entity.setReferenceNumber(dto.getReferenceNumber());
        entity.setInvoiceDate(dto.getInvoiceDate());
        entity.setDescription(dto.getDescription());
        entity.setTax(dto.getTax());
        entity.setAmount(dto.getAmount());
        entity.setCustomerNotes(dto.getCustomerNotes());
        entity.setSubTotal(dto.getSubTotal());
        entity.setRoundOff(dto.getRoundOff());
        entity.setTotal(dto.getTotal());
        entity.setTermsConditions(dto.getTermsConditions());
        entity.setCurrency(dto.getCurrency());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        entity.setStatus(dto.getStatus());

        return entity;
    }
}
