package com.sonalake.meetup.service.web;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class WebServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class, args);
    }

    @Bean
    public Sampler getSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
