package com.example.demo.rest;


import com.example.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // define @postConstruct to load the student data .. only once!

    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();
        theStudents.add(new Student("peter", "parker"));
        theStudents.add(new Student("Mark", "boucher"));
        theStudents.add(new Student("john", "cena"));
    }

    // define endpoint for student- return a list of students

    @GetMapping("/students")
    public List<Student> getStudents() {


        System.out.println("student size in method " +theStudents);

        return theStudents;
    }

    // define endpoints or {students/ {studentid}" - return student at index

    @GetMapping("students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        System.out.println("student id check " +studentId);
        System.out.println("student size " +theStudents);

        if((studentId>= theStudents.size()) || (studentId<0)){
            throw new StudentNotFoundException("Student id not found "+ studentId);
        }
        return theStudents.get(studentId);
    }

    // add an exception nandler using @ExceptionHandler




    }

