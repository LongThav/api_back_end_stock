package com.learn.api.utility.Mapper.SaleMapper;

import com.learn.api.lombokDTO.SaleLomBokDTO.AddPackageLamBokDTO;
import com.learn.api.lombokDTO.SaleLomBokDTO.PackageLamBokDTO;
import com.learn.api.models.SaleModel.PackageModel;

public class PackageMapper {
    public static PackageLamBokDTO convertPackageToDTO(PackageModel packageEntity) {
        PackageLamBokDTO lamBokDTO = new PackageLamBokDTO(
                packageEntity.getPackageId(),
                packageEntity.getCustomerId(),
                packageEntity.getSaleOrderId(),
                packageEntity.getPackageSlip(),
                packageEntity.getPackageDate(),
                packageEntity.getOrderNumber(),
                packageEntity.getPackedNumber(),
                packageEntity.getQuantityToPay(),
                packageEntity.getInternalNotes(),
                packageEntity.getItemId(),
                packageEntity.getItemInventory() != null ? packageEntity.getItemInventory().getName() : null,
                packageEntity.getItemInventory() != null ? packageEntity.getItemInventory().getSalesDescription()
                        : null,
                packageEntity.getItemInventory() != null ? packageEntity.getItemInventory().getSku() : null);
        return lamBokDTO;
    }

    public static PackageModel convertToEntity(AddPackageLamBokDTO packageDTO) {
        PackageModel packageModel = new PackageModel();
        packageModel.setPackageId(packageDTO.getPackageId());
        packageModel.setCustomerId(packageDTO.getCustomerId());
        packageModel.setSaleOrderId(packageDTO.getSaleOrderId());
        packageModel.setPackageSlip(packageDTO.getPackageSlip());
        packageModel.setPackageDate(packageDTO.getPackageDate());
        packageModel.setOrderNumber(packageDTO.getOrderNumber());
        packageModel.setPackedNumber(packageDTO.getPackedNumber());
        packageModel.setQuantityToPay(packageDTO.getQuantityToPay());
        packageModel.setInternalNotes(packageDTO.getInternalNotes());
        packageModel.setItemId(packageDTO.getItemId());
        return packageModel;
    }

    public static AddPackageLamBokDTO convertToDTO(PackageModel packageModel) {
        return new AddPackageLamBokDTO(
                packageModel.getPackageId(),
                packageModel.getCustomerId(),
                packageModel.getSaleOrderId(),
                packageModel.getPackageSlip(),
                packageModel.getPackageDate(),
                packageModel.getOrderNumber(),
                packageModel.getPackedNumber(),
                packageModel.getQuantityToPay(),
                packageModel.getInternalNotes(),
                packageModel.getItemId());
    }

}
