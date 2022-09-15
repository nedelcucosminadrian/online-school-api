package com.example.onlineschoolapp.services;

import com.example.onlineschoolapp.exceptions.CourseNameAlreadyExists;
import com.example.onlineschoolapp.exceptions.CourseNotFoundById;
import com.example.onlineschoolapp.exceptions.NoCourseException;
import com.example.onlineschoolapp.models.Course;
import com.example.onlineschoolapp.repository.CourseRepo;
import com.example.onlineschoolapp.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private CourseRepo courseRepo;

    public CourseService(CourseRepo courseRepo) {//injectare prin constructor
        this.courseRepo = courseRepo;
    }

    public List<Course> getCourses(){

        List<Course>courses=courseRepo.findAll();

        if(courses.isEmpty()){
            throw new NoCourseException("Course list empty");
        }
        return  courses;
    }


    public Course getCourseById(long id){
        Optional<Course> course = courseRepo.findById(id);
        if (course.equals(Optional.empty())==false)
            return course.get();
        throw new CourseNotFoundById(id);
    }


    public void addCourse(Course c){

        Optional<Course> existingCourse = courseRepo.getCourseByName(c.getName());
        if(existingCourse.equals(Optional.empty())){
            throw new CourseNameAlreadyExists();
        }
        else{
            courseRepo.save(c);
        }

    }
}