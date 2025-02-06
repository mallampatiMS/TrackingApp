package com.tracking.controller;

import com.tracking.model.TrackingRequest;
import com.tracking.model.TrackingResponse;
import com.tracking.service.TrackingNumberService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.Map;
import java.util.HashMap;
import com.tracking.exception.ValidationException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class TrackingNumberController {

    @Autowired
    private TrackingNumberService trackingNumberService;

    @RateLimiter(name = "trackingApiRateLimiter", fallbackMethod = "fallbackForRateLimiter")
    @PostMapping(value = "/next-tracking-number", produces = "application/json")
    public TrackingResponse getNextTrackingNumber(@RequestBody @Valid  TrackingRequest trackingRequest) {
        //System.out.println("Received request weight:" + trackingRequest.getWeight() + ":");

        //Manually validate customerSlug after basic validation
           String expectedSlug = convertToSlug(trackingRequest.getCustomerName());
            if (!expectedSlug.equals(trackingRequest.getCustomerSlug())) {
                Map<String, String> errors = new HashMap<>();
                errors.put("customerSlug", "customer_slug should be '" + expectedSlug + "'");
                throw new ValidationException(errors);
            }
           

       
       return trackingNumberService.generateTrackingNumber(trackingRequest);
    }

    public TrackingResponse fallbackForRateLimiter(TrackingRequest request, Exception ex) {
        return new TrackingResponse("RATE_LIMIT_EXCEEDED", Instant.ofEpochMilli(System.currentTimeMillis()));
    }

    @PostMapping("/test")
    public void testJsonMapping(@RequestBody String json) {
        System.out.println("Received JSON: " + json);
    }

    private String convertToSlug(String name) {
        return name.toLowerCase().replace(" ", "-").replaceAll("[^a-z0-9-]", "");  // Simple slug conversion
    }
}
