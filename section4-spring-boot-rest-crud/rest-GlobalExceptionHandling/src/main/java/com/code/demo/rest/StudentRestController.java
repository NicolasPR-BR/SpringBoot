package com.code.demo.rest;

import com.code.demo.entity.Student;
import com.code.demo.entity.StudentErrorResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> students = new ArrayList<>();
    //Endpoint

    @PostConstruct
    private void populateList() {
        students.add(new Student("Lisa", "Simpson"));
        students.add(new Student("Bart", "Simpson"));
        students.add(new Student("Homer", "Simpson"));
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }


    @GetMapping("/students/{studentId}") //{studentId} is a path variable
    public Student getStudentById(@PathVariable int studentId){ //@PathVariable binds studentId from the URL to the integer
        if(studentId <= 0 || studentId >= students.size()){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return students.get(studentId);
    }

}