package com.tracking.controller;

import com.tracking.model.TrackingRequest;
import com.tracking.model.TrackingResponse;
import com.tracking.service.TrackingNumberService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class TrackingNumberController {

    @Autowired
    private TrackingNumberService trackingNumberService;

    @RateLimiter(name = "trackingApiRateLimiter", fallbackMethod = "fallbackForRateLimiter")
    @PostMapping(value = "/next-tracking-number", produces = "application/json")
    public TrackingResponse getNextTrackingNumber(@Valid @RequestBody TrackingRequest request) {
        return trackingNumberService.generateTrackingNumber(request);
    }

    public TrackingResponse fallbackForRateLimiter(TrackingRequest request, Exception ex) {
        return new TrackingResponse("RATE_LIMIT_EXCEEDED", Instant.ofEpochMilli(System.currentTimeMillis()));
    }

    @PostMapping("/test")
    public void testJsonMapping(@RequestBody String json) {
        System.out.println("Received JSON: " + json);
    }
}
