package com.caching.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * Configuration class for setting up caching using Caffeine.
 * This class enables caching in the application and configures the cache manager with specific cache characteristics.
 */
@Configuration
@EnableCaching
public class CacheConfig {

    private static final Logger logger = LoggerFactory.getLogger(CacheConfig.class);

    /**
     * Creates and configures a cache manager for the application.
     *
     * @return The CacheManager instance, configured with caffeine cache.
     */
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("geocoding", "reverse-geocoding");
        cacheManager.setCaffeine(caffeineCacheBuilder());
        return cacheManager;
    }

    /**
     * Configures the caffeine cache builder with specific properties such as expiration time and maximum size.
     * It also logs the eviction of cache entries for monitoring purposes.
     *
     * @return A Caffeine<Object, Object> cache builder configured with eviction policy and maximum size.
     */
    private Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                .expireAfterAccess(1, TimeUnit.MINUTES) // Cache expiration time
                .maximumSize(50) // Maximum number of entries in the cache
                .evictionListener((key, value, cause) ->
                        logger.info("Evicted key={}, Cause={}", key, cause)
                );
    }
}
