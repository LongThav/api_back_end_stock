package com.learn.api.controllers.SaleController;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.api.config.DuplicateInvoiceNumberException;
import com.learn.api.dto.ResponseWrapper;
import com.learn.api.dto.AuthDto.AuthRespone;
import com.learn.api.dto.SaleDTO.AddInvoiceResponseDTO;
import com.learn.api.dto.SaleDTO.InvoiceCustomerDTO;
import com.learn.api.models.SaleModel.InvoiceModel;

import com.learn.api.service.SaleService.InvoiceService.InvoiceService;
import com.learn.api.utility.Mapper.SaleMapper.AddInvoiceResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/sale")
@Validated
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    private boolean isTokenMissing(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        return authorizationHeader == null || !authorizationHeader.startsWith("Bearer ");
    }

    @GetMapping("/invoice")
    public ResponseEntity<?> getAllInvoicEntity(
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
        Page<InvoiceCustomerDTO> invoice = invoiceService.getInvoice(pageable);
        // Check if there are no items on the requested page
        if (invoice.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(404, "No invoice found", invoice));
        }
        // Return the paginated response
        return ResponseEntity.ok(new ResponseWrapper<>(200, "Response invoice successfully", invoice));
    }

    @PostMapping("/customer/{customerId}/add-invoice")
    public ResponseEntity<?> addRemarkToCustomer(
            @PathVariable(value = "customerId") Long customerId,
            @Valid @RequestBody InvoiceModel invoiceModel) {
        try {
            // Add the report to the customer
            InvoiceModel invoice = invoiceService.addInvoice(customerId, invoiceModel);

            if (invoice == null) {
                return ResponseEntity.badRequest().body(new ResponseWrapper<>(400, "Customer not found", null));
            }

            AddInvoiceResponseDTO invoiceCustomerDTO = AddInvoiceResponse.toDTO(invoice);

            return ResponseEntity.ok(new ResponseWrapper<>(200, "Remark added successfully", invoiceCustomerDTO));
        } catch (DuplicateInvoiceNumberException ex) {
            Map<String, String> errors = new HashMap<>();
            errors.put("retainer_invoice_number", ex.getMessage());
            return ResponseEntity.ok(new ResponseWrapper<>(500, "An unexpected error occurred", errors));
        } catch (RuntimeException ex) {
            Map<String, String> errors = new HashMap<>();
            System.out.println("Another");
            errors.put("general", ex.getMessage());
            return ResponseEntity.ok(new ResponseWrapper<>(500, "An unexpected error occurred", errors));
        }
    }

}
