package com.learn.api.models.SaleModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tbl_contact_person")
public class ContactPersonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_person_id")
    private Integer contactPersonId;

    @NotNull(message = "Customer id is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerModel customer;

    @Size(max = 10)
    @NotNull(message = "Salutation is required")
    @Column(name = "salutation")
    private String salutation;

    @NotNull
    @Size(max = 255)
    @NotNull(message = "First name is required")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Size(max = 255)
    @NotNull(message = "Last name is required")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Email
    @Size(max = 255)
    @NotNull(message = "Email address is required")
    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @Size(max = 50)
    @NotNull(message = "Work phone is required")
    @Column(name = "work_phone")
    private String workPhone;

    @Size(max = 50)
    @NotNull(message = "Mobile phone is required")
    @Column(name = "mobile_phone")
    private String mobilePhone;

    // Getters and Setters

    public Integer getContactPersonId() {
        return contactPersonId;
    }

    public void setContactPersonId(Integer contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    // public CustomerModel getCustomer() {
    //     return customer;
    // }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
}
