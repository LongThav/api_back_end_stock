package com.learn.api.dto.SaleDTO;

import java.math.BigDecimal;
import java.sql.Date;

public class SaleOrderDTO {
    private Long id;
    private Date salesOrderDate;
    private String salesOrderNumber;
    private String referenceNumber;
    private String customerName;
    private String status;
    private BigDecimal amount;
    private boolean invoiced;
    private String payment;
    private String currency;

    // Getters and Setters
    public Date getSalesOrderDate() {
        return salesOrderDate;
    }

    public void setSalesOrderDate(Date salesOrderDate) {
        this.salesOrderDate = salesOrderDate;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public boolean isInvoiced() {
        return invoiced;
    }

    public void setInvoiced(boolean invoiced) {
        this.invoiced = invoiced;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
