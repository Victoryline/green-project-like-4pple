package org.example.restserver.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/v1/sse")
public class SseController {
    private final ConcurrentHashMap<String, SseEmitter> clients = new ConcurrentHashMap<>();

    @GetMapping("/subscribe/{clientId}")
    public SseEmitter subscribe(@PathVariable String clientId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        clients.put(clientId, emitter);

        emitter.onCompletion(() -> clients.remove(clientId));
        emitter.onTimeout(() -> clients.remove(clientId));

        return emitter;
    }

    @PostMapping("/send/{clientId}")
    public void sendMessage(@PathVariable String clientId, @RequestBody String message) {
        SseEmitter emitter = clients.get(clientId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().data(message));
            } catch (IOException e) {
                clients.remove(clientId);
            }
        }
    }
}
