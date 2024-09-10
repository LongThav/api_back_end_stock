package com.learn.api.service.SaleService.SaleOrderService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.learn.api.dto.SaleDTO.SaleOrderDTO;
import com.learn.api.models.SaleModel.SaleOrderModel;

public interface SaleOrderService {

    Page<SaleOrderDTO> getSaleOrder(Pageable pageable);

    SaleOrderModel addSaleOrder(Long customerId, SaleOrderModel saleOrderModel);
}
