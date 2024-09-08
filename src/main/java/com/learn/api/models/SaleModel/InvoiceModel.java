package com.learn.api.models.SaleModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_invoice")
public class InvoiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Long invoiceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerModel customer;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    @Column(name = "billing_address", nullable = false)
    private String billingAddress;

    @Column(name = "billing_city", nullable = false)
    private String billingCity;

    @Column(name = "billing_state", nullable = false)
    private String billingState;

    @Column(name = "billing_zip_code", nullable = false)
    private String billingZipCode;

    @Column(name = "billing_country", nullable = false)
    private String billingCountry;

    @Column(name = "billing_phone", nullable = false)
    private String billingPhone;

    @Column(name = "billing_fax")
    private String billingFax;

    @Column(name = "retainer_invoice_number", nullable = false)
    private String retainerInvoiceNumber;

    @Column(name = "reference_number")
    private String referenc;

    @Column(name = "retainer_invoice_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date retainerInvoiceDate;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "tax", nullable = false)
    private BigDecimal tax = BigDecimal.ZERO;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

    @Column(name = "customer_notes", length = 500)
    private String customerNotes;

    @Column(name = "sub_total", nullable = false)
    private BigDecimal subTotal = BigDecimal.ZERO;

    @Column(name = "round_off", nullable = false)
    private BigDecimal roundOff = BigDecimal.ZERO;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(name = "terms_conditions", length = 1000)
    private String termsConditions;

    @Column(name = "status", nullable = false)
    private String status;

    // Getters and Setters
    public Long geInvoicetId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingZipCode() {
        return billingZipCode;
    }

    public void setBillingZipCode(String billingZipCode) {
        this.billingZipCode = billingZipCode;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingPhone() {
        return billingPhone;
    }

    public void setBillingPhone(String billingPhone) {
        this.billingPhone = billingPhone;
    }

    public String getBillingFax() {
        return billingFax;
    }

    public void setBillingFax(String billingFax) {
        this.billingFax = billingFax;
    }

    public String getRetainerInvoiceNumber() {
        return retainerInvoiceNumber;
    }

    public void setRetainerInvoiceNumber(String retainerInvoiceNumber) {
        this.retainerInvoiceNumber = retainerInvoiceNumber;
    }

    public String getReferenc() {
        return referenc;
    }

    public void setReferenc(String referenc) {
        this.referenc = referenc;
    }

    public Date getRetainerInvoiceDate() {
        return retainerInvoiceDate;
    }

    public void setRetainerInvoiceDate(Date retainerInvoiceDate) {
        this.retainerInvoiceDate = retainerInvoiceDate;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTermsConditions() {
        return termsConditions;
    }

    public void setTermsConditions(String termsConditions) {
        this.termsConditions = termsConditions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
