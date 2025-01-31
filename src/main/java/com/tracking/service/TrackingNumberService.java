package com.tracking.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.tracking.model.TrackingRequest;
import com.tracking.model.TrackingResponse;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;

@Service
public class TrackingNumberService {
    
    private final Cache<String, Boolean> generatedTrackingNumbersCache;
    private final AtomicInteger trackingNumberCounter = new AtomicInteger();
    
    public TrackingNumberService() {
        generatedTrackingNumbersCache = Caffeine.newBuilder()
                .expireAfterWrite(24, TimeUnit.HOURS)
                .maximumSize(100000)
                .build();
    }

    public TrackingResponse generateTrackingNumber(TrackingRequest request) {
        String trackingNumber;
        do {
            trackingNumber = generateUniqueTrackingNumber();
        } while (generatedTrackingNumbersCache.asMap().containsKey(trackingNumber));

        generatedTrackingNumbersCache.put(trackingNumber, Boolean.TRUE);

        return new TrackingResponse(trackingNumber, Instant.ofEpochMilli(System.currentTimeMillis()));
    }

    private String generateUniqueTrackingNumber() {
        String generated = generateRandomTrackingNumber();
        return generated.toUpperCase();
    }

    private String generateRandomTrackingNumber() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder trackingNumber = new StringBuilder();

        Random rand = new Random();
        for (int i = 0; i < 16; i++) {
            int randomIndex = rand.nextInt(chars.length());
            trackingNumber.append(chars.charAt(randomIndex));
        }
        
        String counterSuffix = String.format("%04d", trackingNumberCounter.incrementAndGet());
        trackingNumber.append(counterSuffix);
        
        return trackingNumber.toString();
    }
}
