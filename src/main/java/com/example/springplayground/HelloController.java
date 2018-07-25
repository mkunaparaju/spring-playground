package com.example.springplayground;

import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpMethod.POST;

@RestController
public class HelloController {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String helloWorld() {
        return "Hello from Spring!";
    }

    @GetMapping("/tasks")
    public String getTasks() {
        return "These are tasks";
    }

    @PostMapping("/tasks")
    public String createTask() {
        return "You just POSTed to /tasks";
    }

}