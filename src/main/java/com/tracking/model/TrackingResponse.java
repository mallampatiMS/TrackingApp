package com.tracking.model;

import java.time.Instant;

public class TrackingResponse {
    private String trackingNumber;
    private Instant createdAt;

    // Constructor, Getters, Setters
    public TrackingResponse(String trackingNumber, Instant createdAt) {
        this.trackingNumber = trackingNumber;
        this.createdAt = createdAt;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
