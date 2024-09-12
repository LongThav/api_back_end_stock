package com.learn.api.lombokDTO.SaleLomBokDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddPackageLamBokDTO {
    private Integer packageId;

    @JsonProperty("customerId")
    @NotNull(message = "Customer ID cannot be null")
    private Integer customerId;

    @JsonProperty("saleOrderId")
    @NotNull(message = "Sale Order ID cannot be null")
    private Integer saleOrderId;

    @JsonProperty("packageSlip")
    @NotNull(message = "Package Slip cannot be null")
    @Size(max = 255, message = "Package Slip cannot be more than 255 characters")
    private String packageSlip;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Package Date cannot be null")
    private LocalDate packageDate;

    @JsonProperty("orderNumber")
    @NotNull(message = "Order Number cannot be null")
    private Integer orderNumber;

    @JsonProperty("packedNumber")
    @NotNull(message = "Packed Number cannot be null")
    private Integer packedNumber;

    @JsonProperty("quantityToPay")
    @NotNull(message = "Quantity to Pay cannot be null")
    private Integer quantityToPay;

    @JsonProperty("internalNotes")
    @NotNull(message = "Internal Notes cannot be null")
    @Size(max = 400, message = "Internal Notes cannot be more than 400 characters")
    private String internalNotes;

    @JsonProperty("itemId")
    @NotNull(message = "Item ID cannot be null")
    private Integer itemId;
}
