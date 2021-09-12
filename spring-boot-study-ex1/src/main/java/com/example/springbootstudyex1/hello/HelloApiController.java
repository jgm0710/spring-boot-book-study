package com.example.springbootstudyex1.hello;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApiController {

    @GetMapping("/api/hello")
    public String[] helloApi() {
        return new String[]{"hello", "world"};
    }
}
