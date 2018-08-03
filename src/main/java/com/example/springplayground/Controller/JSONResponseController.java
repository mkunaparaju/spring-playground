package com.example.springplayground.Controller;

import com.example.springplayground.Model.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/json")
public class JSONResponseController {


    @RequestMapping("/object")
    public Person getJsonObject() {
        Person p = new Person();
        p.setName("Dwayne");
        p.setAge("12");
        return p;
    }

    @RequestMapping("/object-array")
    public List<Person> getPersonArray(){
        ArrayList<Person> ap = new ArrayList<Person>();
        Person p = new Person();
        p.setName("Dwayne");
        p.setAge("12");

        ap.add(p);
        return ap;
    }
}
