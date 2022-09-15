package com.example.onlineschoolapp.controller;

import com.example.onlineschoolapp.models.Course;
import com.example.onlineschoolapp.services.CourseService;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.CoderResult;
import java.util.List;

@RestController
@RequestMapping("online-school/api/v1/courses")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all-courses")
    public ResponseEntity<List<Course>> getCourses(){
         return  new ResponseEntity<List<Course>>(this.courseService.getCourses(), HttpStatus.OK);
    }

    @GetMapping("/course-by-id/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable long id){
        return new ResponseEntity<Course>(this.courseService.getCourseById(id), HttpStatus.OK);
    }

    @PostMapping("/add-course")
    public ResponseEntity<String> addCourse(@Valid @RequestBody Course c){
        this.courseService.addCourse(c);
        return new ResponseEntity<>("added course",HttpStatus.CREATED);
    }
}
