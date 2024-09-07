package com.learn.api.utility.Mapper.SaleMapper;

import com.learn.api.dto.SaleDTO.CustomerDTO;
import com.learn.api.dto.SaleDTO.ReportDTO;
import com.learn.api.models.SaleModel.CustomerModel;
import com.learn.api.models.SaleModel.ReportModel;

public class CustomerMapper {
    public static CustomerDTO convertCustomerToDTO(CustomerModel customerModel) {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(customerModel.getCustomerId());
        dto.setCustomerType(customerModel.getCustomerType());
        dto.setSalutation(customerModel.getSalutation());
        dto.setFirstName(customerModel.getFirstName());
        dto.setLastName(customerModel.getLastName());
        dto.setCompanyName(customerModel.getCompanyName());
        dto.setCustomerEmail(customerModel.getCustomerEmail());
        dto.setCustomerPhone(customerModel.getCustomerPhone());
        dto.setWorkPhone(customerModel.getWorkPhone());
        dto.setMobilePhone(customerModel.getMobilePhone());
        return dto;
    }

    public static ReportDTO convertReportToDTO(ReportModel reportModel) {
        ReportDTO dto = new ReportDTO();
        dto.setReportId(reportModel.getReportId());
        dto.setCupiditate(reportModel.getCupiditate());
        dto.setFuga(reportModel.getFuga());
        return dto;
    }
}
