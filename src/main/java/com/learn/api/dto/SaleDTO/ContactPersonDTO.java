package com.learn.api.dto.SaleDTO;

import java.util.Set;

import com.learn.api.models.SaleModel.ContactPersonModel;

public class ContactPersonDTO {

    private Integer contactPersonId;

    private Long customerId;

    private String salutation;

    private String firstName;

    private String lastName;

    private String emailAddress;

    private String workPhone;

    private String mobilePhone;

    private Set<ContactPersonModel> contactPersonModels;

    public Set<ContactPersonModel> getContact() {
        return contactPersonModels;
    }

    public void setContact(Set<ContactPersonModel> contactPersonModels) {
        this.contactPersonModels = contactPersonModels;
    }

    // Getters and Setters

    public Integer getContactPersonId() {
        return contactPersonId;
    }

    public void setContactPersonId(Integer contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    public Long getCustomer() {
        return customerId;
    }

    public void setCustomer(CustomerDTO customerId) {
        this.customerId = customerId.getCustomerId();
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
