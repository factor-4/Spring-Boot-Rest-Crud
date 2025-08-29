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

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
        // create a studentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return a responseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // add another exceeption handler .. to catch any exception (catch all)

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
        // create a studentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return a responseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    }

