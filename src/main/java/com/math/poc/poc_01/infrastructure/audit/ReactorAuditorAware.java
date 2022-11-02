package com.math.poc.poc_01.infrastructure.audit;

import com.math.poc.poc_01.infrastructure.config.web.ReactiveRequestContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;


@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableR2dbcAuditing
public class ReactorAuditorAware {

    private final LoggedUserRequest loggedUserRequest;

    @Bean
    ReactiveAuditorAware<String> auditorAware() {
        return ()-> ReactiveRequestContextHolder.getRequest()
                .mapNotNull(req -> req.getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .flatMap(loggedUserRequest::getLoggedUser)
                .subscribeOn(Schedulers.parallel()).log();

    }
}


