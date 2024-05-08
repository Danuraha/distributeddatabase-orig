package com.example.distributeddatabase.Controller;

import com.example.distributeddatabase.Model.Student;
import com.example.distributeddatabase.Model.User;
import com.example.distributeddatabase.Service.StudentService;
import com.example.distributeddatabase.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/cache-api")
public class UserController {


    private final UserService service;


    private final StudentService studentService;

    @GetMapping("/getStudents")
    public List<Student> getAllStudents(){
        return studentService.getStudents();
    }


    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {

        return service.getUsers();
    }

    @GetMapping("/getUserById/{id}")
    public User getUser(@PathVariable int id) {

        return service.getUser(id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {

        return service.delete(id);
    }


    //manual cache refreshing
    @PostMapping("/refreshCache")
    public String refreshCache() {
        service.refreshCache();
        return "User cache refreshed!";
    }
}