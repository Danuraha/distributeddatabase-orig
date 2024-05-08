package com.example.distributeddatabase.Repository;

import com.example.distributeddatabase.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
