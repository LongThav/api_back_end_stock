package com.learn.api.models.SaleModel;

import com.learn.api.constants.Enum.SaleEnum.AddressTypeEnum;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tbl_address")
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    // @NotNull(message = "Customer Id is required")
    private CustomerModel customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type", nullable = false)
    @NotNull(message = "Address Type is required")
    private AddressTypeEnum addressType;

    @Size(max = 255, message = "Attention cannot exceed 255 characters")
    @Column(name = "attention")
    private String attention;

    @Size(max = 255, message = "Country/Region cannot exceed 255 characters")
    @NotNull(message = "Country is required")
    @Column(name = "country_region")
    private String countryRegion;

    @Size(max = 255, message = "Address Street 1 cannot exceed 255 characters")
    @NotNull(message = "Address street_1 is required")
    @Column(name = "address_street_1")
    private String addressStreet1;

    @Size(max = 255, message = "Address Street 2 cannot exceed 255 characters")
    @Column(name = "address_street_2")
    private String addressStreet2;

    @NotNull(message = "City is required")
    @Size(max = 255, message = "City cannot exceed 255 characters")
    @Column(name = "city")
    private String city;

    @Size(max = 255, message = "State cannot exceed 255 characters")
    @Column(name = "state")
    private String state;

    @Size(max = 20, message = "Zip Code cannot exceed 20 characters")
    @Column(name = "zip_code")
    private String zipCode;

    @Size(max = 20, message = "Phone number cannot exceed 20 characters")
    @NotNull(message = "Phone is required")
    @Column(name = "phone")
    private String phone;

    @Size(max = 20, message = "Fax number cannot exceed 20 characters")
    @Column(name = "fax_number")
    private String faxNumber;

    @Column(name = "is_billing_address")
    private boolean isBillingAddress;

    @Column(name = "is_shipping_address")
    private boolean isShippingAddress;

    // Getters and Setters
    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    // public CustomerModel getCustomer() {
    //     return customer;
    // }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public AddressTypeEnum getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressTypeEnum addressType) {
        this.addressType = addressType;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public String getAddressStreet1() {
        return addressStreet1;
    }

    public void setAddressStreet1(String addressStreet1) {
        this.addressStreet1 = addressStreet1;
    }

    public String getAddressStreet2() {
        return addressStreet2;
    }

    public void setAddressStreet2(String addressStreet2) {
        this.addressStreet2 = addressStreet2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public boolean isBillingAddress() {
        return isBillingAddress;
    }

    public void setBillingAddress(boolean billingAddress) {
        isBillingAddress = billingAddress;
    }

    public boolean isShippingAddress() {
        return isShippingAddress;
    }

    public void setShippingAddress(boolean shippingAddress) {
        isShippingAddress = shippingAddress;
    }
}
