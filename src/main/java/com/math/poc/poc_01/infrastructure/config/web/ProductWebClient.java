package com.math.poc.poc_01.infrastructure.config.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductWebClient {

    @Value("${koral.url:https://localhost:8080}")
    private String koralUrl;

    @Bean
    public WebClient client(){
        return
                WebClient.create(koralUrl);
    }

}
