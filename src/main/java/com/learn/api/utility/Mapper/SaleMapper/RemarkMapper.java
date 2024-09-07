package com.learn.api.utility.Mapper.SaleMapper;

import com.learn.api.dto.SaleDTO.RemarkDTO;
import com.learn.api.models.SaleModel.CustomerModel;
import com.learn.api.models.SaleModel.RemarkModel;

public class RemarkMapper {
    public static RemarkDTO toDto(RemarkModel remark) {
        RemarkDTO dto = new RemarkDTO();
        dto.setRemarkId(remark.getRemarkId());
        dto.setRemark(remark.getRemark());
        return dto;
    }

    public static RemarkModel toEntity(RemarkDTO dto, CustomerModel customer) {
        RemarkModel remark = new RemarkModel();
        remark.setRemarkId(dto.getRemarkId());
        remark.setCustomer(customer);
        remark.setRemark(dto.getRemark());
        return remark;
    }

}
