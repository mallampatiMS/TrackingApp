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
    //@NotBlank
    
    @NotNull(message = "originCountryId must not be null")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Invalid origin country code")
    private String originCountryId;

    @NotNull(message = "destinationCountryId must not be null")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Invalid destination country code")
    private String destinationCountryId;

    @NotNull(message = "weight must not be null")
    @Pattern(regexp = "^\\d+(\\.\\d{1,3})?$", message = "Invalid weight")
    private Double weight;

    @NotNull(message = "createdAt must not be null")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}([+-]\\d{2}:\\d{2}|Z)$", message = "Invalid timestamp")
    private String createdAt;

    @NotNull(message = "customerId must not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private UUID customerId;

    @NotNull(message = "customerName must not be null")
    private String customerName;

    @ValidCustomerSlug
    @NotNull(message = "customerSlug must not be null")
    @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$", message = "Invalid slug format")
    private String customerSlug;

    // Getters and Setters
}
