package com.learn.api.dto.SaleDTO;

import java.math.BigDecimal;
import java.sql.Date;

public class AddSaleOrderDTO {
    private Long id;
    private Long customerId;
    private Long item_id;
    private String salesOrderNumber;
    private String referenceNumber;
    private Date salesOrderDate;
    private Date expectedShipmentDate;
    private String paymentTerms;
    private String deliveryMethod;
    private String salesperson;
    private String customerNotes;
    private BigDecimal subTotal;
    private BigDecimal shippingCharges;
    private BigDecimal taxOnShipping;
    private BigDecimal adjustment;
    private BigDecimal roundOff;
    private BigDecimal totalAmount;
    private String termsAndConditions;


        // Getters and Setters
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

    public Long getItemId() {
        return item_id;
    }

    public void setItemId(Long itemId) {
        this.item_id = itemId;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Date getSalesOrderDate() {
        return salesOrderDate;
    }

    public void setSalesOrderDate(Date salesOrderDate) {
        this.salesOrderDate = salesOrderDate;
    }

    public Date getExpectedShipmentDate() {
        return expectedShipmentDate;
    }

    public void setExpectedShipmentDate(Date expectedShipmentDate) {
        this.expectedShipmentDate = expectedShipmentDate;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getSalesperson() {
        return salesperson;
    }

    public void setSalesperson(String salesperson) {
        this.salesperson = salesperson;
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

    public BigDecimal getShippingCharges() {
        return shippingCharges;
    }

    public void setShippingCharges(BigDecimal shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    public BigDecimal getTaxOnShipping() {
        return taxOnShipping;
    }

    public void setTaxOnShipping(BigDecimal taxOnShipping) {
        this.taxOnShipping = taxOnShipping;
    }

    public BigDecimal getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(BigDecimal adjustment) {
        this.adjustment = adjustment;
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

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }
}
