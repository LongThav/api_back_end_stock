package com.learn.api.service.SaleService;

// import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.learn.api.models.SaleModel.AddressModel;
import com.learn.api.models.SaleModel.CustomerModel;
import com.learn.api.repositorys.SaleRepository.AddressRepository;
import com.learn.api.repositorys.SaleRepository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Page<CustomerModel> getAllCustomer(Pageable pageable) {
        Page<CustomerModel> customer = customerRepository.findAll(pageable);
        return customer;
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
}
