package com.math.poc.poc_01.infrastructure.audit;

import com.math.poc.poc_01.infrastructure.config.web.ProductWebClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoggedUserRequest {

    private final ProductWebClient http;

    public Mono<String> getLoggedUser(String token) {
        token = removePrefix(token);
        return http.client().get()
                .uri("/user-id/{token}", token)
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorReturn(token);
    }

    private String removePrefix(String token) {
       return token.replace("Bearer ", "");
    }


    public Scheduler scheduler() {
        return Schedulers.boundedElastic();
    }
}
