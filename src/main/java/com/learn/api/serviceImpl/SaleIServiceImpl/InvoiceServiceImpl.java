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
import com.learn.api.dto.SaleDTO.RetainerInvoiceDTO;
import com.learn.api.models.InventoryModel.ItemModel;
import com.learn.api.models.SaleModel.CustomerModel;
import com.learn.api.models.SaleModel.InvoiceModel;
import com.learn.api.models.SaleModel.RetainerInvoiceModel;
import com.learn.api.repositorys.InventoryRepository.ItemRepository;
import com.learn.api.repositorys.SaleRepository.CustomerRepository;
import com.learn.api.repositorys.SaleRepository.InvoiceRepository;
import com.learn.api.repositorys.SaleRepository.RetainerInvoiceRepository;
import com.learn.api.service.SaleService.InvoiceService.InvoiceService;
import com.learn.api.utility.Mapper.SaleMapper.InvoiceMapper;
import com.learn.api.utility.Mapper.SaleMapper.RetainerInvoiceMapper;

import jakarta.transaction.Transactional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RetainerInvoiceRepository retainerInvoiceRepository;

    @Autowired
    private ItemRepository itemRepository;

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
    public InvoiceModel addInvoice(Long customerId, Long itemId, InvoiceModel invoiceModel) {
        // Check if the retainer_invoice_number already exists
        Optional<InvoiceModel> existingInvoice = invoiceRepository
                .findByRetainerInvoiceNumber(invoiceModel.getRetainerInvoiceNumber());
        if (existingInvoice.isPresent()) {
            throw new DuplicateInvoiceNumberException("The retainer_invoice_number is already in use.");
        }

        // item
        ItemModel item = itemRepository.findById(itemId)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Item not found with id: " + invoiceModel.getItemInventory().getItemID()));
        invoiceModel.setItemInvenotory(item);

        // Proceed with saving the new invoice
        CustomerModel customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        invoiceModel.setCustomer(customer);
        invoiceModel.setItemInvenotory(item);
        invoiceModel.setStatus(InvoiceStatueEnum.Pending.toString()); // Ensure this value is valid

        return invoiceRepository.save(invoiceModel);
    }

    @Override
    public Page<RetainerInvoiceDTO> getRetainerInvoice(Pageable pageable) {
        Page<RetainerInvoiceModel> retainerInvoice = retainerInvoiceRepository.findAll(pageable);
        // Force initialization of proxies
        retainerInvoice.forEach(invoice -> invoice.getCustomer().getCustomerId());
        List<RetainerInvoiceDTO> invoiceDTO = retainerInvoice.stream()
                .map(RetainerInvoiceMapper::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(invoiceDTO, pageable, retainerInvoice.getTotalElements());
    }

    @Override
    @Transactional
    public RetainerInvoiceModel addRetainerInvoice(Long customerId, RetainerInvoiceModel retainerInvoiceModel) {
        // Check if the retainer_invoice_number already exists
        Optional<RetainerInvoiceModel> existingRetainerInvoice = retainerInvoiceRepository
                .findByRetainerInvoiceNumber(retainerInvoiceModel.getRetainerInvoiceNumber());
        if (existingRetainerInvoice.isPresent()) {
            throw new DuplicateInvoiceNumberException("The retainer_invoice_number is already in use.");
        }

        // Proceed with saving the new invoice
        CustomerModel customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        retainerInvoiceModel.setCustomer(customer);
        retainerInvoiceModel.setStatus(InvoiceStatueEnum.Pending.toString()); // Ensure this value is valid

        return retainerInvoiceRepository.save(retainerInvoiceModel);
    }

}
