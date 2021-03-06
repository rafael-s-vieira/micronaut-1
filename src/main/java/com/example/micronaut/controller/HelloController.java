package com.example.micronaut.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller
public class HelloController {

    @Get(value = "/hello", produces = { MediaType.TEXT_PLAIN })
    public String index() {
        return "Hello World from Micronaut!";
    }
}
