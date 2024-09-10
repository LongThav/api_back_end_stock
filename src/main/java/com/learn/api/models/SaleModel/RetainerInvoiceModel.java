package com.learn.api.models.SaleModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_retainer_invoice")
public class RetainerInvoiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "retainer_invoice_number is required")
    @Column(name = "retainer_invoice_number", nullable = false, length = 50)
    private String retainerInvoiceNumber;

    @NotNull(message = "reference_number is required")
    @Column(name = "reference_number", length = 50)
    private String referenceNumber;

    @NotNull(message = "invoice_date is required")
    @Column(name = "invoice_date", nullable = false)
    private LocalDateTime invoiceDate;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "DECIMAL(10, 2) DEFAULT 0.00")
    private BigDecimal tax;

    @Column(columnDefinition = "DECIMAL(10, 2) DEFAULT 0.00")
    private BigDecimal amount;

    @Column(columnDefinition = "TEXT")
    private String customerNotes;

    @Column(columnDefinition = "DECIMAL(10, 3) DEFAULT 0.000")
    private BigDecimal subTotal;

    @Column(columnDefinition = "DECIMAL(10, 3) DEFAULT 0.000")
    private BigDecimal roundOff;

    @Column(columnDefinition = "DECIMAL(10, 3) DEFAULT 0.000")
    private BigDecimal total;

    @Column(columnDefinition = "TEXT")
    private String termsConditions;

    @NotNull(message = "currency is required")
    @Column(name = "currency", length = 3, nullable = false, columnDefinition = "VARCHAR(3) DEFAULT 'USD'")
    private String currency;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "status", length = 20, nullable = false, columnDefinition = "VARCHAR(20) DEFAULT 'Pending'")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerModel customer;

    public RetainerInvoiceModel() {
    }

    public RetainerInvoiceModel(CustomerModel customer, String retainerInvoiceNumber, String referenceNumber,
            LocalDateTime invoiceDate, String description, BigDecimal tax, BigDecimal amount, String customerNotes,
            BigDecimal subTotal, BigDecimal roundOff, BigDecimal total, String termsConditions, String currency, String status) {
        this.customer = customer;
        this.retainerInvoiceNumber = retainerInvoiceNumber;
        this.referenceNumber = referenceNumber;
        this.invoiceDate = invoiceDate;
        this.description = description;
        this.tax = tax;
        this.amount = amount;
        this.customerNotes = customerNotes;
        this.subTotal = subTotal;
        this.roundOff = roundOff;
        this.total = total;
        this.termsConditions = termsConditions;
        this.currency = currency;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public String getRetainerInvoiceNumber() {
        return retainerInvoiceNumber;
    }

    public void setRetainerInvoiceNumber(String retainerInvoiceNumber) {
        this.retainerInvoiceNumber = retainerInvoiceNumber;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCustomerNotes() {
        return customerNotes;
    }

    public void setCustomerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getRoundOff() {
        return roundOff;
    }

    public void setRoundOff(BigDecimal roundOff) {
        this.roundOff = roundOff;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getTermsConditions() {
        return termsConditions;
    }

    public void setTermsConditions(String termsConditions) {
        this.termsConditions = termsConditions;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
