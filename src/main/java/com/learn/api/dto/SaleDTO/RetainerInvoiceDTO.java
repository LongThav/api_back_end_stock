package com.learn.api.dto.SaleDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RetainerInvoiceDTO {
    private Long id;
    private Long customerId;
    private String customerName;
    private String retainerInvoiceNumber;
    private String referenceNumber;
    private LocalDateTime invoiceDate;
    private BigDecimal amount;
    private BigDecimal total;
    private String currency;
    private String status;

    public RetainerInvoiceDTO() {
    }

    public RetainerInvoiceDTO(Long id, Long customerId, String retainerInvoiceNumber, String referenceNumber,
            LocalDateTime invoiceDate, String description, BigDecimal tax, BigDecimal amount, String customerNotes,
            BigDecimal subTotal, BigDecimal roundOff, BigDecimal total, String termsConditions, String currency, String status) {
        this.id = id;
        this.customerId = customerId;
        this.retainerInvoiceNumber = retainerInvoiceNumber;
        this.referenceNumber = referenceNumber;
        this.invoiceDate = invoiceDate;
        this.amount = amount;
        this.total = total;
        this.currency = currency;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getRetainerInvoiceNumber() {
        return retainerInvoiceNumber ;
    }

    public void setRetainerInvoiceNumber(String retainerInvoiceNumber) {
        this.retainerInvoiceNumber = retainerInvoiceNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
