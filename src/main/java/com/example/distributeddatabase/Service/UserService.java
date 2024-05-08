package com.example.distributeddatabase.Service;


import com.example.distributeddatabase.Repository.UserRepository;
import com.example.distributeddatabase.Model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository repository;


    @Cacheable(cacheNames = { "userCache" })
    public List<User> getUsers() {

        log.info("Hit DB 1st time in getUsers()");
        return (List<User>) repository.findAll();

    }

    @Cacheable(value = "userCache", key = "#id", unless = "#result==null")
    public User getUser(int id) {
        log.info("Hit DB 1st time in getUser()");

        return repository.findById(id).orElse(null);
    }

    @CacheEvict(value = "userCache", allEntries = true)
    public String delete(int id) {
        repository.deleteById(id);
        return "User deleted with id " + id;
    }

    // manual cache refreshing
    @CacheEvict(value = "userCache", allEntries = true)
    public void refreshCache() {

        log.info("User Cache refreshed manually!");
    }

    // Scheduled cache refresh every 60 seconds
    @CacheEvict(value = "userCache", allEntries = true)
    @Scheduled(fixedRate = 60000)  // 60 seconds in milliseconds
    public void scheduledCacheRefresh() {
        try {
            refreshCache();
            log.info("User Cache refreshed automatically!");

        } catch (Exception e) {
            log.error("Error refreshing cache:", e);
        }
    }
}