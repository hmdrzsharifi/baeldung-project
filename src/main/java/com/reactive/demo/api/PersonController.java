package com.reactive.demo.api;

import com.reactive.demo.domain.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;


@RestController
@RequestMapping(value = "/api")
public class PersonController {

    @GetMapping(path = "/person", produces = "text/event-stream")
    Flux<Person> getPersonStream() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(index -> new Person(index, "foo"));
    }

}
