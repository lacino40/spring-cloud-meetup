package com.sonalake.meetup.eureka;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

import static java.lang.Boolean.TRUE;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

    @Bean
    public Sampler getSampler(@Value("zipkin.sampler.enabled") String enabled) {
        return new Sampler() {
            @Override
            public boolean isSampled(long traceId) {
                return TRUE.toString().equals(enabled);
            }
        };
    }
}
