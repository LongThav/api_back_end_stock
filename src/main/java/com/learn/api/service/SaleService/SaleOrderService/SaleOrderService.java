package com.learn.api.service.SaleService.SaleOrderService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.learn.api.dto.SaleDTO.SaleOrderDTO;
import com.learn.api.lombokDTO.SaleLomBokDTO.AddPackageLamBokDTO;
import com.learn.api.lombokDTO.SaleLomBokDTO.PackageLamBokDTO;
import com.learn.api.models.SaleModel.PackageModel;
import com.learn.api.models.SaleModel.SaleOrderModel;

public interface SaleOrderService {

    Page<SaleOrderDTO> getSaleOrder(Pageable pageable);

    SaleOrderModel addSaleOrder(Long customerId, Long itemId, SaleOrderModel saleOrderModel);

    Page<PackageLamBokDTO> getAllPackages(Pageable pageable);

    PackageModel addPackage(AddPackageLamBokDTO packageLamBokDTO);
}

// https://chatgpt.com/c/66e050db-6860-8006-bcc1-fdf18d31d77c
