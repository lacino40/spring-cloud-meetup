package com.sonalake.meetup.service.weather;

import brave.sampler.Sampler;
import com.sonalake.meetup.service.weather.impl.WeatherServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.ofDefaults;
import static io.github.resilience4j.timelimiter.TimeLimiterConfig.custom;
import static java.time.Duration.ofSeconds;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(WeatherProperties.class)
public class WeatherServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeatherServiceApplication.class, args);
    }

    @Bean
    public Sampler getSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WeatherService forecastService(RestTemplate restTemplate, WeatherProperties weatherProperties) {
        return new WeatherServiceImpl(restTemplate, weatherProperties);
    }

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> openWeatherCircuitBreaker() {
        return factory ->
                factory.configureDefault(
                        id -> new Resilience4JConfigBuilder(id)
                                .timeLimiterConfig(
                                        custom().timeoutDuration(ofSeconds(2)).build()
                                )
                                .circuitBreakerConfig(ofDefaults())
                                .build()
                );
    }
}
