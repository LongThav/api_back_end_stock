package com.learn.api.controllers.SaleController;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.api.constants.Error.ErrorDetail;
import com.learn.api.constants.Error.PackageSaveResponse;
import com.learn.api.constants.HeaderReq.HeaderReqToken;
import com.learn.api.dto.ResponseWrapper;
import com.learn.api.dto.AuthDto.AuthRespone;
import com.learn.api.lombokDTO.SaleLomBokDTO.AddPackageLamBokDTO;
import com.learn.api.lombokDTO.SaleLomBokDTO.PackageLamBokDTO;
import com.learn.api.models.SaleModel.PackageModel;
import com.learn.api.service.SaleService.SaleOrderService.SaleOrderService;
import com.learn.api.utility.Mapper.SaleMapper.PackageMapper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/sale")
@Validated
public class PackageController {
    private SaleOrderService saleOrderService;

    public PackageController(SaleOrderService saleOrderService) {
        this.saleOrderService = saleOrderService;
    }

    @GetMapping("/package")
    public ResponseEntity<?> getAllInvoicEntity(
            HttpServletRequest httpRequest,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        // Check if the token is missing
        if (HeaderReqToken.isTokenMissing(httpRequest)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthRespone(false, "User not authorized"));
        }
        // Create a Pageable object
        Pageable pageable = PageRequest.of(page, size);
        // Retrieve a paginated list of items

        // Retrieve a paginated list of CustomerDTO
        Page<PackageLamBokDTO> packageDto = saleOrderService.getAllPackages(pageable);
        // Check if there are no items on the requested page
        if (packageDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(404, "No package found", packageDto));
        }
        // Return the paginated response
        return ResponseEntity.ok(new ResponseWrapper<>(200, "Response package successfully", packageDto));
    }

    //add package
    @PostMapping("/add-packages")
    public ResponseEntity<?> createPackages(@Valid @RequestBody List<AddPackageLamBokDTO> packageDTOs) {
        List<AddPackageLamBokDTO> savedPackages = new ArrayList<>();
        List<ErrorDetail> errors = new ArrayList<>();

        for (AddPackageLamBokDTO packageDTO : packageDTOs) {
            try {
                PackageModel packageModel = saleOrderService.addPackage(packageDTO);
                // Convert the saved PackageModel back to AddPackageLamBokDTO for consistency
                AddPackageLamBokDTO savedPackageDTO = PackageMapper.convertToDTO(packageModel);
                savedPackages.add(savedPackageDTO);
            } catch (DataIntegrityViolationException e) {
                // Handle duplicate entry error
                errors.add(new ErrorDetail(packageDTO.getSaleOrderId(), "Duplicate entry: " + e.getMessage()));
            } catch (Exception e) {
                // Handle other potential exceptions
                errors.add(new ErrorDetail(packageDTO.getSaleOrderId(), "Error processing package: " + e.getMessage()));
            }
        }

        if (!errors.isEmpty()) {
            // Return a response with errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseWrapper<>(400, "Some records could not be saved",
                            new PackageSaveResponse(savedPackages, errors)));
        }

        return ResponseEntity.ok(new ResponseWrapper<>(200, "Packages added successfully", savedPackages));
    }

}
