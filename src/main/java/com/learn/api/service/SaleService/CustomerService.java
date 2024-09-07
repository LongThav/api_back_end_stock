package com.learn.api.service.SaleService;

import java.util.List;
import java.util.stream.Collectors;

// import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.learn.api.dto.SaleDTO.CustomerDTO;
import com.learn.api.dto.SaleDTO.ReportDTO;
import com.learn.api.models.SaleModel.AddressModel;
import com.learn.api.models.SaleModel.ContactPersonModel;
import com.learn.api.models.SaleModel.CustomerModel;
import com.learn.api.models.SaleModel.ReportModel;
import com.learn.api.repositorys.SaleRepository.AddressRepository;
import com.learn.api.repositorys.SaleRepository.ContactPersonRepository;
import com.learn.api.repositorys.SaleRepository.CustomerRepository;
import com.learn.api.repositorys.SaleRepository.ReportRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ContactPersonRepository contactPersonRepository;

    public Page<CustomerDTO> getAllCustomer(Pageable pageable) {
        Page<CustomerModel> customerPage = customerRepository.findAll(pageable);

        // Convert the CustomerModel to CustomerDTO
        List<CustomerDTO> customerDTOList = customerPage.stream()
                .map(this::convertCustomerToDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(customerDTOList, pageable, customerPage.getTotalElements());
    }

    public CustomerModel getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    public CustomerModel addCustomer(CustomerModel customer) {
        return customerRepository.save(customer);
    }

    public Page<AddressModel> getAllAddress(Pageable pageable) {
        Page<AddressModel> address = addressRepository.findAll(pageable);
        return address;
    }

    public AddressModel getAddressById(Long addressId) {
        return addressRepository.findById(addressId).orElse(null);
    }

    @Transactional
    public CustomerModel addAddressToCustomer(Long customerId, AddressModel newAddress) {
        CustomerModel customer = customerRepository.findById(customerId).orElse(null);

        if (customer == null) {
            return null;
        }

        newAddress.setCustomer(customer); // Set the customer on the address
        customer.getAddresses().add(newAddress); // Add the new address to the customer's addresses

        customerRepository.save(customer); // Save the updated customer
        return customer;
    }

    public Page<ContactPersonModel> getAllContactPerson(Pageable pageable) {
        Page<ContactPersonModel> address = contactPersonRepository.findAll(pageable);
        return address;
    }

    @Transactional
    public CustomerModel addContact(Long customerId, ContactPersonModel contactPerson) {
        CustomerModel customer = customerRepository.findById(customerId).orElse(null);

        if (customer == null) {
            return null;
        }

        contactPerson.setCustomer(customer); // Set the customer on the address
        customer.getContact().add(contactPerson); // Add the new address to the customer's addresses

        customerRepository.save(customer); // Save the updated customer
        return customer;
    }

    public Page<ReportDTO> getAllReport(Pageable pageable) {
        Page<ReportModel> reportPage = reportRepository.findAll(pageable);

        // Convert the CustomerModel to CustomerDTO
        List<ReportDTO> reportDTO = reportPage.stream()
                .map(this::convertReportToDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(reportDTO, pageable, reportPage.getTotalElements());
    }

    @Transactional
    public ReportModel  addReportToCustomer(Long customerId, ReportModel reportModel) {
        CustomerModel customer = customerRepository.findById(customerId).orElse(null);

        if (customer == null) {
            return null;
        }

        reportModel.setCustomer(customer); // Set the customer on the address
        customer.getReport().add(reportModel); // Add the new address to the customer's addresses

        ReportModel savedReport = reportRepository.save(reportModel); 
        return savedReport;
    }

    private CustomerDTO convertCustomerToDTO(CustomerModel customerModel) {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(customerModel.getCustomerId());
        dto.setCustomerType(customerModel.getCustomerType());
        dto.setSalutation(customerModel.getSalutation());
        dto.setFirstName(customerModel.getFirstName());
        dto.setLastName(customerModel.getLastName());
        dto.setCompanyName(customerModel.getCompanyName());
        dto.setCustomerEmail(customerModel.getCustomerEmail());
        dto.setCustomerPhone(customerModel.getCustomerPhone());
        dto.setWorkPhone(customerModel.getWorkPhone());
        dto.setMobilePhone(customerModel.getMobilePhone());
        return dto;
    }

    private ReportDTO convertReportToDTO(ReportModel reportModel) {
        ReportDTO dto = new ReportDTO();
        dto.setReportId(reportModel.getReportId());
        // dto.setCustomerId(reportModel.getCustomer().getCustomerId());
        dto.setCupiditate(reportModel.getCupiditate());
        dto.setFuga(reportModel.getFuga());
        return dto;
    }
}
