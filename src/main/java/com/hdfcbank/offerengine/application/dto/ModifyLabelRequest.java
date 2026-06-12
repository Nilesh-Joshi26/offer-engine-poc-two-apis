package com.hdfcbank.offerengine.application.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ModifyLabelRequest(
        @Size(max = 500) String description,
        @NotNull @FutureOrPresent LocalDate expiryDate,
        @NotBlank @Size(max = 50) String updatedBy
) {
}
