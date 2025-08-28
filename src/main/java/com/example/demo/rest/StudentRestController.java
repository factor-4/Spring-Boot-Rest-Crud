package com.example.demo.rest;


import com.example.demo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    // define endpoint for student- return a list of students

    @GetMapping("/students")
    public List<Student> getStudents() {

        List<Student> theStudents = new ArrayList<>();
        theStudents.add(new Student("peter", "parker"));
        theStudents.add(new Student("Mark", "boucher"));
        theStudents.add(new Student("john", "cena"));

        return theStudents;
    }

}

