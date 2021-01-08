package com.malsolo.springframework.boot.docker;

import lombok.AllArgsConstructor;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class HelloWorldController {

    private final ApplicationEventPublisher publisher;

    @GetMapping("/hello")
    public Mono<String> hello() throws InterruptedException {
        Thread.sleep(5000);
        return Mono.just("Hello World");
    }

    @GetMapping("/up")
    public Mono<String> up() {
        AvailabilityChangeEvent.publish(publisher, this, ReadinessState.ACCEPTING_TRAFFIC);
        return Mono.just("up");
    }

    @GetMapping("/down")
    public Mono<String> down() {
        AvailabilityChangeEvent.publish(publisher, this, ReadinessState.REFUSING_TRAFFIC);
        return Mono.just("down");
    }
}
