package com.learn.api.models.SaleModel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_report")
public class ReportModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;
   
    @ManyToOne(fetch = FetchType.LAZY)
    // @NotNull(message = "Customer_id is required")
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerModel customer;

    @NotNull(message = "Cupiditate is required")
    @Column(name = "cupiditate", columnDefinition = "TEXT")
    private String cupiditate;

    
    @Column(name = "fuga", columnDefinition = "TEXT")
    private String fuga;

    // Getters and Setters
    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    // public CustomerModel getCustomer() {
    //     return customer;
    // }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public String getCupiditate() {
        return cupiditate;
    }

    public void setCupiditate(String cupiditate) {
        this.cupiditate = cupiditate;
    }

    public String getFuga() {
        return fuga;
    }

    public void setFuga(String fuga) {
        this.fuga = fuga;
    }
}
