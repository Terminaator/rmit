package eu.api.infrastructure.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
class CorsConfig {

    @Value("#{'${cors.allowed.origins:http://localhost:3000}'.split(',')}")
    private List<String> origins;

    @Bean
    CorsWebFilter corsWebFilter() {
        log.info("corsWebFilter: {}", origins);

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(origins);
        config.setAllowedMethods(Arrays.asList("GET", "POST"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/application", config);
        source.registerCorsConfiguration("/application/service", config);

        return new CorsWebFilter(source);
    }
}
