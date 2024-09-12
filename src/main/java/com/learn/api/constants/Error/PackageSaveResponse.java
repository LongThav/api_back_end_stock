package com.learn.api.constants.Error;

import java.util.List;

import com.learn.api.lombokDTO.SaleLomBokDTO.AddPackageLamBokDTO;

public class PackageSaveResponse {
    private List<AddPackageLamBokDTO> savedPackages;
    private List<ErrorDetail> errors;

    public PackageSaveResponse(List<AddPackageLamBokDTO> savedPackages, List<ErrorDetail> errors) {
        this.savedPackages = savedPackages;
        this.errors = errors;
    }

    public List<AddPackageLamBokDTO> getSavedPackages() {
        return savedPackages;
    }

    public List<ErrorDetail> getErrors() {
        return errors;
    }
}
