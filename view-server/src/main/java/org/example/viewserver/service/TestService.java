package org.example.viewserver.service;

import reactor.core.publisher.Mono;

public interface TestService {
    Mono<Object> getTestMessage();
}
