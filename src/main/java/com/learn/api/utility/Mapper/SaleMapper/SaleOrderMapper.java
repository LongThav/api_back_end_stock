package com.learn.api.utility.Mapper.SaleMapper;

import com.learn.api.dto.SaleDTO.AddSaleOrderDTO;
import com.learn.api.dto.SaleDTO.SaleOrderDTO;
import com.learn.api.models.SaleModel.SaleOrderModel;

public class SaleOrderMapper {
    public static SaleOrderDTO convertSaleOrderToDTO(SaleOrderModel saleOrderModel) {
        if (saleOrderModel == null) {
            return null;
        }

        SaleOrderDTO dto = new SaleOrderDTO();
        dto.setId(saleOrderModel.getId());
        dto.setSalesOrderDate(saleOrderModel.getSalesOrderDate());
        dto.setSalesOrderNumber(saleOrderModel.getSalesOrderNumber());
        dto.setReferenceNumber(saleOrderModel.getReferenceNumber());
        dto.setCustomerName(
                saleOrderModel.getCustomer().getFirstName() + " " + saleOrderModel.getCustomer().getLastName());
        dto.setStatus(saleOrderModel.getStatus());
        dto.setAmount(saleOrderModel.getTotalAmount());
        dto.setCurrency(saleOrderModel.getCurrency());
        dto.setInvoiced(false);
        dto.setPayment(saleOrderModel.getPaymentTerms());
        return dto;
    }

    public static AddSaleOrderDTO toSaleOrderModel(SaleOrderModel saleOrderModel) {
        if (saleOrderModel == null) {
            return null;
        }

        AddSaleOrderDTO dto = new AddSaleOrderDTO();

        dto.setId(saleOrderModel.getId()); // Corrected
        dto.setCustomerId(saleOrderModel.getCustomer().getCustomerId()); // Corrected
        dto.setSalesOrderNumber(saleOrderModel.getSalesOrderNumber()); // Corrected
        dto.setReferenceNumber(saleOrderModel.getReferenceNumber()); // Corrected
        dto.setSalesOrderDate(saleOrderModel.getSalesOrderDate()); // Corrected
        dto.setExpectedShipmentDate(saleOrderModel.getExpectedShipmentDate()); // Corrected
        dto.setPaymentTerms(saleOrderModel.getPaymentTerms()); // Corrected
        dto.setDeliveryMethod(saleOrderModel.getDeliveryMethod()); // Corrected
        dto.setSalesperson(saleOrderModel.getSalesperson()); // Corrected
        dto.setCustomerNotes(saleOrderModel.getCustomerNotes()); // Corrected
        dto.setSubTotal(saleOrderModel.getSubTotal()); // Corrected
        dto.setShippingCharges(saleOrderModel.getShippingCharges()); // Corrected
        dto.setTaxOnShipping(saleOrderModel.getTaxOnShipping()); // Corrected
        dto.setAdjustment(saleOrderModel.getAdjustment()); // Corrected
        dto.setRoundOff(saleOrderModel.getRoundOff()); // Corrected
        dto.setTotalAmount(saleOrderModel.getTotalAmount()); // Corrected
        dto.setTermsAndConditions(saleOrderModel.getTermsAndConditions()); // Corrected

        return dto;
    }

}
