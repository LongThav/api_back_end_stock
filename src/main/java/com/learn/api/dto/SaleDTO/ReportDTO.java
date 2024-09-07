package com.learn.api.dto.SaleDTO;

public class ReportDTO {
    private Long reportId;
    private Long customerId;
    private String cupiditate;
    private String fuga;

    // Constructors
    public ReportDTO() {
    }

    public ReportDTO(Long reportId, Long customerId, String cupiditate, String fuga) {
        this.reportId = reportId;
        this.customerId = customerId;
        this.cupiditate = cupiditate;
        this.fuga = fuga;
    }

    // Getters and Setters
    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
