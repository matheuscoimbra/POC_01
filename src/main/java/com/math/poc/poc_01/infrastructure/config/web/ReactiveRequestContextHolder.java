package com.math.poc.poc_01.infrastructure.config.web;

import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

public class ReactiveRequestContextHolder {

    private ReactiveRequestContextHolder() {}
    public static final Class<ServerHttpRequest> CONTEXT_KEY = ServerHttpRequest.class;

    public static Mono<ServerHttpRequest> getRequest() {
        return Mono.just(ReactiveRequestContextHolder.CONTEXT_KEY)
                .flatMap(ctx ->Mono.deferContextual(ct -> Mono.justOrEmpty(ct.get(CONTEXT_KEY)))
                        .contextWrite(ct -> ct.put(CONTEXT_KEY, ct.get(CONTEXT_KEY))));
    }
}