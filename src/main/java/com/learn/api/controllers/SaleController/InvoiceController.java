package com.learn.api.controllers.SaleController;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.api.dto.ResponseWrapper;
import com.learn.api.dto.AuthDto.AuthRespone;
import com.learn.api.dto.SaleDTO.InvoiceCustomerDTO;
import com.learn.api.service.SaleService.InvoiceService.InvoiceService;

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

}
