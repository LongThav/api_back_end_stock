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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.learn.api.models.InventoryModel.ItemModel;

@Entity
@Table(name = "tbl_invoice")
public class InvoiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Long invoiceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    // @NotBlank(message = "Item_id inventory is required")
    @JoinColumn(name = "item_id", nullable = false)
    private ItemModel item_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerModel customer;

    @NotBlank(message = "Currency is required")
    @Size(max = 3, message = "Currency must be 3 characters long")
    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    @NotBlank(message = "Billing address is required")
    @Column(name = "billing_address", nullable = false)
    private String billingAddress;

    @NotBlank(message = "Billing city is required")
    @Column(name = "billing_city", nullable = false)
    private String billingCity;

    @NotBlank(message = "Billing state is required")
    @Column(name = "billing_state", nullable = false)
    private String billingState;

    @NotBlank(message = "Billing zip code is required")
    @Column(name = "billing_zip_code", nullable = false)
    private String billingZipCode;

    @NotBlank(message = "Billing country is required")
    @Column(name = "billing_country", nullable = false)
    private String billingCountry;

    @NotBlank(message = "Billing phone is required")
    @Column(name = "billing_phone", nullable = false)
    private String billingPhone;

    @Column(name = "billing_fax")
    private String billingFax;

    @NotBlank(message = "Retainer invoice number is required")
    @Column(name = "retainer_invoice_number", nullable = false)
    private String retainerInvoiceNumber;

    @Column(name = "reference_number")
    private String referenceNumber;

    @NotNull(message = "Retainer invoice date is required")
    @Column(name = "retainer_invoice_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date retainerInvoiceDate;

    @Size(max = 500, message = "Description can be up to 500 characters long")
    @Column(name = "description", length = 500)
    private String description;

    @NotNull(message = "Tax is required")
    @DecimalMin(value = "0.00", message = "Tax cannot be negative")
    @Column(name = "tax", nullable = false)
    private BigDecimal tax = BigDecimal.ZERO;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.00", message = "Amount cannot be negative")
    @Column(name = "amount", nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

    @Size(max = 500, message = "Customer notes can be up to 500 characters long")
    @Column(name = "customer_notes", length = 500)
    private String customerNotes;

    @NotNull(message = "Sub total is required")
    @DecimalMin(value = "0.00", message = "Sub total cannot be negative")
    @Column(name = "sub_total", nullable = false)
    private BigDecimal subTotal = BigDecimal.ZERO;

    @NotNull(message = "Round off is required")
    @DecimalMin(value = "0.00", message = "Round off cannot be negative")
    @Column(name = "round_off", nullable = false)
    private BigDecimal roundOff = BigDecimal.ZERO;

    @NotNull(message = "Total amount is required")
    @DecimalMin(value = "0.00", message = "Total amount cannot be negative")
    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Size(max = 1000, message = "Terms and conditions can be up to 1000 characters long")
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
        return referenceNumber;
    }

    public void setReferenc(String referenc) {
        this.referenceNumber = referenc;
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

    public void setItemInvenotory(ItemModel itemModel){
        this.item_id = itemModel;
    }

    public ItemModel getItemInventory(){
        return item_id;
    }
}
