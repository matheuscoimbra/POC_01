package com.math.poc.poc_01.infrastructure.config.web;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebClient {

    @Value("${koral.url:}")
    private String koralUrl;

}
