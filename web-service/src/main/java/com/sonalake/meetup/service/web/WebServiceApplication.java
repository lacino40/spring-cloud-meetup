package com.sonalake.meetup.service.web;

import brave.sampler.Sampler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang.BooleanUtils.isFalse;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class WebServiceApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebServiceApplication.class);
    private static final List<String> supportedConfigurations = newArrayList("basic", "feign", "gateway");

    public static void main(String[] args) {
        checkConfigurationVMOption();

        SpringApplication.run(WebServiceApplication.class, args);
    }

    @Bean
    public Sampler getSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

    private static void checkConfigurationVMOption() {
        String configVMOption = "service.web.config";
        Optional<String> configurationType = Optional.ofNullable(System.getProperty(configVMOption));

        configurationType.ifPresentOrElse(
                type -> {
                    if (isFalse(supportedConfigurations.contains(type))) {
                        System.setProperty(configVMOption, "basic");
                    }
                },
                () -> System.setProperty(configVMOption, "basic"));

        LOGGER.info("applying [{}] configuration", System.getProperty(configVMOption));
    }
}
