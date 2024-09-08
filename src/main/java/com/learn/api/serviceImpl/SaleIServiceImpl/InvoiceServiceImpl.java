package com.learn.api.serviceImpl.SaleIServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.learn.api.dto.SaleDTO.InvoiceCustomerDTO;
import com.learn.api.models.SaleModel.InvoiceModel;
import com.learn.api.repositorys.SaleRepository.InvoiceRepository;
import com.learn.api.service.SaleService.InvoiceService.InvoiceService;
import com.learn.api.utility.Mapper.SaleMapper.InvoiceMapper;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Page<InvoiceCustomerDTO> getInvoice(Pageable pageable) {
        Page<InvoiceModel> invoiceModel = invoiceRepository.findAll(pageable);
        // Force initialization of proxies
        invoiceModel.forEach(invoice -> invoice.getCustomer().getCustomerId());
        List<InvoiceCustomerDTO> invoiceDTO = invoiceModel.stream()
                .map(InvoiceMapper::mapToInvoiceDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(invoiceDTO, pageable, invoiceModel.getTotalElements());
    }

}
