package com.learn.api.models.SaleModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_customer")
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(name = "customer_type", nullable = false)
    private String customerType;

    @Column(name = "salutation")
    private String salutation;

    @NotNull(message = "First name is required")
    @Size(max = 255, message = "First name cannot exceed 255 characters")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull(message = "Last name is required")
    @Size(max = 255, message = "Last name cannot exceed 255 characters")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull(message = "Company Name is required")
    @Size(max = 255, message = "Company name cannot exceed 255 characters")
    @Column(name = "company_name")
    private String companyName;

    // @Column(name = "customer_display_name", nullable = false)
    // private String customerDisplayName;

    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 255, message = "Email cannot exceed 255 characters")
    @Column(name = "customer_email", nullable = false, unique = true)
    private String customerEmail;

    @Column(name = "customer_phone", nullable = false)
    private String customerPhone;

    @NotNull(message = "Work phone is required")
    @Size(max = 20, message = "Work phone cannot exceed 20 characters")
    @Column(name = "work_phone")
    private String workPhone;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<AddressModel> addresse;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ContactPersonModel> contactPersonModels;

    // Getters and Setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Set<AddressModel> getAddresses() {
        return addresse;
    }

    public void setAddresses(Set<ContactPersonModel> contactPersonModels) {
        this.contactPersonModels = contactPersonModels;
    }

    public Set<AddressModel> getContact() {
        return addresse;
    }

    public void setContact(Set<ContactPersonModel> contactPersonModels) {
        this.contactPersonModels = contactPersonModels;
    }
}
