package com.math.poc.poc_01.infrastructure.audit;

import com.math.poc.poc_01.infrastructure.config.web.ReactiveRequestContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.http.HttpHeaders;


@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableR2dbcAuditing
public class ReactiveAuditorAware {

    @Bean
    org.springframework.data.domain.ReactiveAuditorAware<String> auditorAware() {
        return ()-> ReactiveRequestContextHolder.getRequest()
                .map(req -> {
                    HttpHeaders headers = req.getHeaders();
                    String s = headers.get(HttpHeaders.AUTHORIZATION).get(0);
                    log.info("s: {}", s);
                    return s;
                });
    }
}


