package com.reactive.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		// web client
		WebClient client = WebClient.create("http://localhost:8090/api");
		Flux<String> personFlux = client.get()
				.uri("/person")
				.retrieve()
				.bodyToFlux(String.class);

		personFlux.subscribe(System.out::println);
	}

}
