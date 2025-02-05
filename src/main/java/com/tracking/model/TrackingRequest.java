package com.tracking.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import com.tracking.validation.ValidCustomerSlug;
import jakarta.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter  // Generates getter methods for all fields
@Setter  // Generates setter methods for all fields
public class TrackingRequest {
    //@NotBlank
    
    @NotNull(message = "originCountryId must not be null")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Invalid origin country code")
    @JsonProperty("origin_country_id")
    private String originCountryId;

    @NotNull(message = "destinationCountryId must not be null")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Invalid destination country code")
    @JsonProperty("destination_country_id")
    private String destinationCountryId;

    @NotNull(message = "weight must not be null")
    //@Pattern(regexp = "^\\d+(\\.\\d{1,3})?$", message = "Invalid weight")
    //@Pattern(regexp = "^\\d{1,10}(\\.\\d{1,3})?$", message = "Weight must be a valid number with up to 3 decimal places")
    @Digits(integer = 10, fraction = 3, message = "Weight can have up to 3 decimal places")
    @DecimalMin(value = "0.001",  message = "Weight must be at least 0.001 kg")    
    //@JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("weight")
    private BigDecimal weight;

    @NotNull(message = "createdAt must not be null")
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime createdAt;

    @NotNull(message = "customerId must not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("customer_id")
    @JsonDeserialize(as = UUID.class)
    private UUID customerId;

    @NotNull(message = "customerName must not be null")
    @JsonProperty("customer_name")
    private String customerName;

    //@ValidCustomerSlug
    @NotNull(message = "customerSlug must not be null")
    @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$", message = "Invalid slug format")
    @JsonProperty("customer_slug")
    private String customerSlug;

    // Getters and Setters
}
