package com.example.springplayground.crud;

import com.example.springplayground.Model.Lesson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
    Lesson findByTitle(String title);

    @Query("select lesson from Lesson lesson where (lesson.deliveredOn between :start and :end)")
    List<Lesson> findBetweenDates(@Param("start") String start,
                                          @Param("end") String end);
}