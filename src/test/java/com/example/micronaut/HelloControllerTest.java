package com.example.micronaut;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.rxjava3.http.client.Rx3HttpClient;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.reactivex.rxjava3.core.Flowable;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
class HelloControllerTest {

    @Inject
    @Client("/")
    Rx3HttpClient client;

    @Test
    void testHello() {
        HttpRequest<String> request = HttpRequest.GET("/hello");
//        String body = client.toBlocking().retrieve(request);
        final Flowable<String> retrieve = client.retrieve(request);

        retrieve.all(s -> {
            System.out.println("Inside lambda: " + s);
            return true;
        }).blockingGet();

/*
        assertNotNull(body);
        assertEquals("Hello World from Micronaut!", body);
*/
    }
}