package com.learn.api.controllers.SaleController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.api.dto.ResponseWrapper;
import com.learn.api.dto.AuthDto.AuthRespone;
import com.learn.api.dto.SaleDTO.CustomerDTO;
import com.learn.api.dto.SaleDTO.OrderDetailDTO;
import com.learn.api.dto.SaleDTO.RemarkDTO;
import com.learn.api.dto.SaleDTO.ReportDTO;
import com.learn.api.models.SaleModel.AddressModel;
import com.learn.api.models.SaleModel.ContactPersonModel;
import com.learn.api.models.SaleModel.CustomerModel;
import com.learn.api.models.SaleModel.RemarkModel;
import com.learn.api.models.SaleModel.ReportModel;
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

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/customer/{customerId}/add-contact")
    public ResponseEntity<?> createContactPerson(
            @PathVariable(value = "customerId") Long customerId,
            @Valid @RequestBody ContactPersonModel contactPersonModel) {

        // Add the contact person to the customer
        CustomerModel updatedCustomer = customerService.addContact(customerId, contactPersonModel);

        // Create a response object with customerId and contact person details
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> customerResponse = new HashMap<>();
        customerResponse.put("customerId", updatedCustomer.getCustomerId());

        // Include contact person details in the response
        response.put("customer", customerResponse);
        response.put("salutation", contactPersonModel.getSalutation());
        response.put("firstName", contactPersonModel.getFirstName());
        response.put("lastName", contactPersonModel.getLastName());
        response.put("emailAddress", contactPersonModel.getEmailAddress());
        response.put("workPhone", contactPersonModel.getWorkPhone());
        response.put("mobilePhone", contactPersonModel.getMobilePhone());
        response.put("message", "Contact person added successfully");

        return ResponseEntity.ok(new ResponseWrapper<>(200, "Response contact successfully", response));
    }

    @GetMapping("/report")
    public ResponseEntity<?> getAllReport(
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
        Page<ReportDTO> report = customerService.getAllReport(pageable);
        // Check if there are no items on the requested page
        if (report.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(404, "No report found", report));
        }
        // Return the paginated response
        return ResponseEntity.ok(new ResponseWrapper<>(200, "Response report successfully", report));
    }

    @PostMapping("/customer/{customerId}/add-report")
    public ResponseEntity<?> addReportToCustomer(
            @PathVariable(value = "customerId") Long customerId,
            @Valid @RequestBody ReportModel reportModel) {

        // Add the report to the customer
        ReportModel report = customerService.addReportToCustomer(customerId, reportModel);

        if (report == null) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<>(400, "Customer not found", null));
        }

        ReportDTO reportDTO = new ReportDTO();

        reportDTO.setCustomerId(customerId);
        reportDTO.setReportId(reportModel.getReportId());
        reportDTO.setCupiditate(reportModel.getCupiditate());
        reportDTO.setFuga(reportModel.getFuga());

        return ResponseEntity.ok(new ResponseWrapper<>(200, "Report added successfully", reportDTO));
    }

    @GetMapping("/remark")
    public ResponseEntity<?> getAllRemark(
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
        Page<RemarkDTO> report = customerService.getAllRemark(pageable);
        // Check if there are no items on the requested page
        if (report.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(404, "No report found", report));
        }
        // Return the paginated response
        return ResponseEntity.ok(new ResponseWrapper<>(200, "Response report successfully", report));
    }

    @PostMapping("/customer/{customerId}/add-remark")
    public ResponseEntity<?> addRemarkToCustomer(
            @PathVariable(value = "customerId") Long customerId,
            @Valid @RequestBody RemarkModel remarkModel) {

        // Add the report to the customer
        RemarkModel remakr = customerService.addRemarkToCustomer(customerId, remarkModel);

        if (remakr == null) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<>(400, "Customer not found", null));
        }

        // Map the RemarkModel to RemarkDTO
        RemarkDTO remark = new RemarkDTO();
        remark.setCustomerId(customerId);
        remark.setRemark(remakr.getRemark()); // Set the remark from the saved report
        remark.setRemarkId(remakr.getRemarkId()); // Set the remarkId from the saved report

        return ResponseEntity.ok(new ResponseWrapper<>(200, "Remark added successfully", remark));
    }

    // get order detail
    @GetMapping("/order-detail")
    public ResponseEntity<?> getOrderDetail(
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
        Page<OrderDetailDTO> orderDetailDTO = customerService.getAllOrderDetail(pageable);
        // Check if there are no items on the requested page
        if (orderDetailDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(404, "No report found", orderDetailDTO));
        }
        // Return the paginated response
        return ResponseEntity.ok(new ResponseWrapper<>(200, "Response order detail successfully", orderDetailDTO));
    }

}
