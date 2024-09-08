package com.learn.api.dto.SaleDTO;

import java.math.BigDecimal;
import java.sql.Date;


public class InvoiceCustomerDTO {
    private Long invoiceId;
    private Date date;
    private String retainerInvoice;
    private String reference;

    private String customerName;
    private Long customerId;
    private BigDecimal amount;
    private BigDecimal balance;
    private String status;

    // Getters and Setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRetainInvoice() {
        return retainerInvoice;
    }

    public void setRetainInvoice(String RetainInvoice) {
        this.retainerInvoice = RetainInvoice;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvocie(Long invoice_id) {
        this.invoiceId = invoice_id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
