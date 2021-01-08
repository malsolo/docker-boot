package com.malsolo.springframework.boot.docker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public Mono<String> hello() throws InterruptedException {
        Thread.sleep(5000);
        return Mono.just("Hello World");
    }

}
