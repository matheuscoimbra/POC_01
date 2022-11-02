package com.math.poc.poc_01.infrastructure.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;


public class RequestLoggingDecorator extends ServerHttpRequestDecorator {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLoggingDecorator.class);

    public RequestLoggingDecorator(ServerHttpRequest delegate) {
        super(delegate);
    }

    @Override
    public Flux<DataBuffer> getBody() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        return super.getBody().publishOn(Schedulers.boundedElastic()).doOnNext(dataBuffer -> {
            try {
                Channels.newChannel(baos).write(dataBuffer.toByteBuffer().asReadOnlyBuffer());
                String body = baos.toString(StandardCharsets.UTF_8);
                LOGGER.info("Request: payload={}", body);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
