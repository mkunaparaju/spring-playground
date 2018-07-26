package com.example.springplayground.Controller;

import com.example.springplayground.Model.Person;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class FormDataController {

    @PostMapping("/string-example")
    public String getRawString(@RequestBody String rawBody)    {
        return rawBody;
    }

    @PostMapping("/formMapExample")
    public String getMapString(@RequestParam Map<String, String> body){
        return body.toString();
    }

    @PostMapping("/formObjectExample")
    public String getObjectString(Person person){
        return person.toString();
    }

    @PostMapping("/formMultiExample/{id}/comments")
    public String GetPathFormQueryParamsString(@PathVariable int id,  @RequestParam Map<String,String> params){
        return String.format("id: %d - %s says %s", id,params.get("name"), params.get("content"));
    }

    @GetMapping("/cookie")
    public String getCookie(@CookieValue(name = "foo") String cookie) {
        return cookie;
    }

    @GetMapping("/header")
    public String getHeader(@RequestHeader(name = "Host") String cookie) {
        return cookie;
    }



}
