package com.code.demo.rest;

import com.code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Student getStudentById(@PathVariable int studentId){ //@PathVariable binds studentId from URL to the integer
        if(studentId >= 0 && studentId <= students.size()){
            return students.get(studentId);
        }
        return new Student();
    }

}