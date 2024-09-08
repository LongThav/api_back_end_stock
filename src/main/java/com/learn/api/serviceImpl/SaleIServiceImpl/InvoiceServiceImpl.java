package com.learn.api.serviceImpl.SaleIServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.learn.api.config.DuplicateInvoiceNumberException;
import com.learn.api.constants.Enum.SaleEnum.InvoiceStatueEnum;
import com.learn.api.dto.SaleDTO.InvoiceCustomerDTO;
import com.learn.api.models.SaleModel.CustomerModel;
import com.learn.api.models.SaleModel.InvoiceModel;
import com.learn.api.repositorys.SaleRepository.CustomerRepository;
import com.learn.api.repositorys.SaleRepository.InvoiceRepository;
import com.learn.api.service.SaleService.InvoiceService.InvoiceService;
import com.learn.api.utility.Mapper.SaleMapper.InvoiceMapper;

import jakarta.transaction.Transactional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CustomerRepository customerRepository;

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

    // add invoice
    @Override
    @Transactional
    public InvoiceModel addInvoice(Long customerId, InvoiceModel invoiceModel) {
        // Check if the retainer_invoice_number already exists
        Optional<InvoiceModel> existingInvoice = invoiceRepository.findByRetainerInvoiceNumber(invoiceModel.getRetainerInvoiceNumber());
        if (existingInvoice.isPresent()) {
            throw new DuplicateInvoiceNumberException("The retainer_invoice_number is already in use.");
        }
    
        // Proceed with saving the new invoice
        CustomerModel customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
    
        invoiceModel.setCustomer(customer);
        invoiceModel.setStatus(InvoiceStatueEnum.Pending.toString()); // Ensure this value is valid
    
        return invoiceRepository.save(invoiceModel);
    }
    

}
