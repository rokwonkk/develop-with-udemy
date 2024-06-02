package com.rokwonkk.demo.rest;

import com.rokwonkk.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.SpinnerUI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    // define @PostConstruct to load the student data ... only once

    @PostConstruct
    public void loadData(){
        students = new ArrayList<>();

        students.add(new Student("rok", "won"));
        students.add(new Student("su", "yuen"));
        students.add(new Student("tae", "rin"));
    }

    // define endpoint for "/students" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    // define endpoint or "/students/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        // just index into the list ... keep it simple for now

        // check the studentId again list size

        if((studentId >= students.size()) || (studentId < 0)){
            throw new StudentNotFoundException("Student id not fount - " + studentId);
        }
        return students.get(studentId);
    }

}

















