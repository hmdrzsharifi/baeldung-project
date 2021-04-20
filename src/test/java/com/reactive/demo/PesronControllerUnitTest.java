package com.reactive.demo;

import com.reactive.demo.api.PersonController;
import com.reactive.demo.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@WebFluxTest(controllers = {PersonController.class})
class PesronControllerUnitTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void givenApi_whenCall_thenReceive() {
        List<Person> result = webTestClient
                .get().uri("http://localhost:8090/api/person")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Person.class)
                .getResponseBody()
                .take(3)
                .collectList()
                .block();

        assertThat(result).allSatisfy(person -> assertThat(person.getName()).isEqualTo("foo"));
    }
}