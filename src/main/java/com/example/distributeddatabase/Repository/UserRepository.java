package com.example.distributeddatabase.Repository;

import com.example.distributeddatabase.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}