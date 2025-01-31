package com.tracking.config;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.Duration;

@Configuration
public class RateLimiterConfiguration {

    @Bean
    public RateLimiter rateLimiter() {
         RateLimiterConfig config = RateLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(500)) // Timeout when acquiring a permission
                .limitRefreshPeriod(Duration.ofSeconds(1)) // Refresh rate
                .limitForPeriod(10) // Max requests per period
                .build();

        return RateLimiter.of("trackingRateLimiter", () -> config);  //  Use a Supplier        
    }
}
