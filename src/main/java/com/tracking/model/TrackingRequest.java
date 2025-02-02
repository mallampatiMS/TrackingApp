package com.tracking.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import com.tracking.validation.ValidCustomerSlug;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter  // Generates getter methods for all fields
@Setter  // Generates setter methods for all fields
public class TrackingRequest {
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Z]{2}$", message = "Invalid origin country code")
    private String originCountryId;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Z]{2}$", message = "Invalid destination country code")
    private String destinationCountryId;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\d+(\\.\\d{1,3})?$", message = "Invalid weight")
    private String weight;

    @NotNull
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}([+-]\\d{2}:\\d{2}|Z)$", message = "Invalid timestamp")
    private String createdAt;

    @NotNull
    private UUID customerId;

    @NotNull
    @NotBlank
    private String customerName;

    //@ValidCustomerSlug
    @NotNull
    @NotBlank
    private String customerSlug;

    // Getters and Setters
}
