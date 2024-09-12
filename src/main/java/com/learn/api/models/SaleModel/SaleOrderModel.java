package com.learn.api.models.SaleModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.learn.api.models.InventoryModel.ItemModel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_sale_order")
public class SaleOrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sale_order_id;

    @NotNull(message = "sales_order_number is required")
    @Column(name = "sales_order_number", nullable = false, unique = true, length = 50)
    private String salesOrderNumber;

    @Column(name = "reference_number", length = 50)
    private String referenceNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    // @NotNull(message = "item_id inventory is required")
    @JoinColumn(name = "item_id", nullable = false)
    private ItemModel item_id;

    @NotNull(message = "sales_order_date is required")
    @Column(name = "sales_order_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date salesOrderDate;

    @Column(name = "expected_shipment_date")
    @Temporal(TemporalType.DATE)
    private Date expectedShipmentDate;

    @Column(name = "payment_terms", length = 255)
    private String paymentTerms;

    @Column(name = "delivery_method", length = 255)
    private String deliveryMethod;

    @Column(name = "salesperson", length = 255)
    private String salesperson;

    @Column(name = "customer_notes")
    private String customerNotes;

    @Column(name = "sub_total", precision = 18, scale = 2, columnDefinition = "decimal(18,2) default '0.00'")
    private BigDecimal subTotal;

    @Column(name = "shipping_charges", precision = 18, scale = 2, columnDefinition = "decimal(18,2) default '0.00'")
    private BigDecimal shippingCharges;

    @Column(name = "tax_on_shipping", precision = 18, scale = 2, columnDefinition = "decimal(18,2) default '0.00'")
    private BigDecimal taxOnShipping;

    @Column(name = "adjustment", precision = 18, scale = 2, columnDefinition = "decimal(18,2) default '0.00'")
    private BigDecimal adjustment;

    @Column(name = "round_off", precision = 18, scale = 2, columnDefinition = "decimal(18,2) default '0.00'")
    private BigDecimal roundOff;

    @Column(name = "total_amount", precision = 18, scale = 2, columnDefinition = "decimal(18,2) default '0.00'")
    private BigDecimal totalAmount;

    @Column(name = "terms_and_conditions")
    private String termsAndConditions;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @NotNull(message = "currency is required")
    @Column(name = "currency", length = 3, nullable = false, columnDefinition = "VARCHAR(3) DEFAULT 'USD'")
    private String currency;

    @Column(name = "status", length = 20, nullable = false, columnDefinition = "VARCHAR(20) DEFAULT 'Pending'")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerModel customer;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    // Getters and Setters
    public Long getId() {
        return sale_order_id;
    }

    public void setId(Long id) {
        this.sale_order_id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setItemInvenotory(ItemModel itemModel) {
        this.item_id = itemModel;
    }

    public ItemModel getItemInventory() {
        return item_id;
    }

}
