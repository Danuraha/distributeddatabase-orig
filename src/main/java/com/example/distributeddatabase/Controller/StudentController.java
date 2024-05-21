package com.example.distributeddatabase.Controller;

import com.example.distributeddatabase.Model.Student;
import com.example.distributeddatabase.Model.User;
import com.example.distributeddatabase.Service.StudentService;
import com.example.distributeddatabase.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cache-api")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/getStudents")
    public List<Student> getAllStudents(){

        return studentService.getStudents();
    }

    @GetMapping("/getStudentById/{id}")
    public Student getStudent(@PathVariable int id) {

        return studentService.getStudent(id);
    }



    //manual cache refreshing
    @PostMapping("/studentRefreshCache")
    public String refreshCache() {
        studentService.studentRefreshCache();
        return "Student cache refreshed!";
    }
}