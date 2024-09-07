package com.learn.api.utility.Mapper.SaleMapper;

import com.learn.api.dto.SaleDTO.OrderDetailDTO;
import com.learn.api.models.SaleModel.CustomerModel;
import com.learn.api.models.SaleModel.OrderDetailModel;

public class OrderDetailMapper {
    public static OrderDetailDTO convertOrderDetailToDTO(OrderDetailModel orderDetail) {
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setOrderDetailId(orderDetail.getOrderDetailId());
        dto.setCurrency(orderDetail.getCurrency());
        dto.setTaxRate(orderDetail.getTaxRate());
        dto.setPaymentTerms(orderDetail.getPaymentTerms());
        dto.setPriceList(orderDetail.getPriceList());
        dto.setDocument(orderDetail.getDocument());
        dto.setCreatedAt(orderDetail.getCreatedAt());
        dto.setUpdatedAt(orderDetail.getUpdatedAt());
        return dto;
    }

    public static OrderDetailModel toOrderDetailEntity(OrderDetailDTO dto, CustomerModel customer) {
        OrderDetailModel orderDetail = new OrderDetailModel();
        orderDetail.setCustomer(customer);
        orderDetail.setCurrency(dto.getCurrency());
        orderDetail.setTaxRate(dto.getTaxRate());
        orderDetail.setPaymentTerms(dto.getPaymentTerms());
        orderDetail.setPriceList(dto.getPriceList());
        orderDetail.setDocument(dto.getDocument());
        return orderDetail;
    }
}
