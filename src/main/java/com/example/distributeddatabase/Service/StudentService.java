package com.example.distributeddatabase.Service;

import com.example.distributeddatabase.Model.User;
import com.example.distributeddatabase.Repository.StudentRepository;
import com.example.distributeddatabase.Model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;


    @Cacheable(cacheNames = { "studentCache" })
    public List<Student> getStudents() {

        log.info("Hit DB 1st time in getStudents()");
        return (List<Student>) studentRepository.findAll();

    }

    @Cacheable(value = "studentCache", key = "#id", unless = "#result==null")
    public Student  getStudent(int id) {
        log.info("Hit DB 1st time in getUser()");

        return studentRepository.findById(id).orElse(null);
    }

    @CacheEvict(value = "studentCache", allEntries = true)
    public void studentRefreshCache() {

//        log.info("User Cache refreshed manually!");
    }

    @CacheEvict(value = "studentCache", allEntries = true)
    @Scheduled(fixedRate = 60000)  // 60 seconds in milliseconds
    public void studentsScheduledCacheRefresh( ) {
        try {
            studentRefreshCache();
            log.info("Student Cache refreshed automatically!");

        } catch (Exception e) {
            log.error("Error refreshing cache:", e);

        }}
}
