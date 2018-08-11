package com.example.springplayground.crud;

import com.example.springplayground.Model.Lesson;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
//    Lesson findByTitle(String title);
//    List<Lesson> findByDeliveredOnBetween(Date deliveredOn1, Date deliveredOn2);
}