package com.learn.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.api.dto.ResponseWrapper;
import com.learn.api.dto.AuthDto.AuthRespone;
import com.learn.api.dto.CustomerDTO.ContactPersonDTO;
import com.learn.api.dto.CustomerDTO.CustomerDTO;
import com.learn.api.models.SaleModel.AddressModel;
import com.learn.api.models.SaleModel.ContactPersonModel;
import com.learn.api.models.SaleModel.CustomerModel;
import com.learn.api.service.SaleService.CustomerService;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/sale")
@Validated
public class SaleController {

    @Autowired
    private CustomerService customerService;

    public SaleController(CustomerService customerService) {
        this.customerService = customerService;
    }

    private boolean isTokenMissing(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        return authorizationHeader == null || !authorizationHeader.startsWith("Bearer ");
    }

    @GetMapping("/customer")
    public ResponseEntity<?> getAllCustomer(
            HttpServletRequest httpRequest,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        // Check if the token is missing
        if (isTokenMissing(httpRequest)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthRespone(false, "User not authorized"));
        }
        // Create a Pageable object
        Pageable pageable = PageRequest.of(page, size);
        // Retrieve a paginated list of items

        // Retrieve a paginated list of CustomerDTO
        Page<CustomerDTO> customer = customerService.getAllCustomer(pageable);
        // Check if there are no items on the requested page
        if (customer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(404, "No items found", customer));
        }
        // Return the paginated response
        return ResponseEntity.ok(new ResponseWrapper<>(200, "Response customer successfully", customer));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable(value = "customerId") Long customerId) {
        CustomerModel customer = customerService.getCustomerById(customerId);

        if (customer == null) { // Check if customer is null
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(404, "Customer not found", null));
        }

        // CustomerModel data = customer;
        return ResponseEntity.ok(new ResponseWrapper<>(200, "Response customer successfully", customer));
    }

    @PostMapping("/add-customer")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerModel customer) {
        CustomerModel savedCustomer = customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseWrapper<>(HttpStatus.CREATED.value(), "Customer created successfully", savedCustomer));
    }

    @GetMapping("/address")
    public ResponseEntity<?> getAllAddress(
            HttpServletRequest httpRequest,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        // Check if the token is missing
        if (isTokenMissing(httpRequest)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthRespone(false, "User not authorized"));
        }
        // Create a Pageable object
        Pageable pageable = PageRequest.of(page, size);
        // Retrieve a paginated list of items
        Page<AddressModel> address = customerService.getAllAddress(pageable);
        // Check if there are no items on the requested page
        if (address.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(404, "No address found", address));
        }
        // Return the paginated response
        return ResponseEntity.ok(new ResponseWrapper<>(200, "Response address successfully", address));
    }

    @PostMapping("/customer/{customerId}/address")
    public ResponseEntity<?> addAddressToCustomer(
            @PathVariable(value = "customerId") Long customerId,
            @Valid @RequestBody AddressModel newAddress) {

        CustomerModel updatedCustomer = customerService.addAddressToCustomer(customerId, newAddress);

        if (updatedCustomer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(404, "Customer not found", null));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseWrapper<>(HttpStatus.CREATED.value(), "Customer created successfully", updatedCustomer));
    }

    @GetMapping("/contact")
    public ResponseEntity<?> getAllcontact(
            HttpServletRequest httpRequest,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        // Check if the token is missing
        if (isTokenMissing(httpRequest)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthRespone(false, "User not authorized"));
        }
        // Create a Pageable object
        Pageable pageable = PageRequest.of(page, size);
        // Retrieve a paginated list of items
        Page<ContactPersonModel> contact = customerService.getAllContactPerson(pageable);
        // Check if there are no items on the requested page
        if (contact.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(404, "No contact found", contact));
        }
        // Return the paginated response
        return ResponseEntity.ok(new ResponseWrapper<>(200, "Response contact successfully", contact));
    }

    @PostMapping("/customer/add-contact")
    public ResponseEntity<ContactPersonDTO> createContactPerson(@RequestBody ContactPersonModel contactPersonModel) {
        ContactPersonModel createdContactPerson = customerService.addContact(contactPersonModel);

        // Map the model to the DTO
        ContactPersonDTO contactPersonDTO = new ContactPersonDTO();
        contactPersonDTO.setContactPersonId(createdContactPerson.getContactPersonId());

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(createdContactPerson.getCustomer().getCustomerId());
        contactPersonDTO.setCustomer(customerDTO);

        contactPersonDTO.setSalutation(createdContactPerson.getSalutation());
        contactPersonDTO.setFirstName(createdContactPerson.getFirstName());
        contactPersonDTO.setLastName(createdContactPerson.getLastName());
        contactPersonDTO.setEmailAddress(createdContactPerson.getEmailAddress());
        contactPersonDTO.setWorkPhone(createdContactPerson.getWorkPhone());
        contactPersonDTO.setMobilePhone(createdContactPerson.getMobilePhone());

        return ResponseEntity.ok(contactPersonDTO);
    }

}
