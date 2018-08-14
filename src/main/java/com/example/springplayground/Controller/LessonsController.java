package com.example.springplayground.Controller;

import com.example.springplayground.Model.Lesson;
import com.example.springplayground.crud.LessonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class LessonsController {

    private final LessonRepository repository;

    public LessonsController(LessonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Lesson> lesson(@PathVariable Long id) {
        return this.repository.findById(id);
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

    @DeleteMapping("/{id}")
    public String deleteLesson(@PathVariable Long id) {
        this.repository.deleteById(id);
        return "Deleted";
    }

    @PatchMapping("/{id}")
    public String patchUpdateLesson(@PathVariable Long id, @RequestBody Lesson updatedLesson) throws JsonProcessingException {
        Lesson lesson = this.repository.findById(id).get();
        lesson.setTitle(updatedLesson.getTitle());
        lesson.setDeliveredOn(updatedLesson.getDeliveredOn());
        repository.save(lesson);

        return new ObjectMapper().writeValueAsString(lesson);
    }

    @GetMapping("/find/{title}")
    public Lesson getLessonsByTitle(@PathVariable String title) {
        return this.repository.findByTitle(title);
    }

    @GetMapping("/between")
    public List<Lesson> between(@RequestParam String date1,
                                    @RequestParam String date2){
        return repository.findBetweenDates(date1, date2);
    }
}