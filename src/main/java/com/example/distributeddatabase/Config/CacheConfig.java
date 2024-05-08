package com.example.distributeddatabase.Config;


import com.hazelcast.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
//
//@Configuration
//@EnableScheduling
//public class CacheConfig {
//
//    @Bean
//    public Config configure() {
//        return new Config()
//                .setInstanceName("hazelcast-instance")
//                .addMapConfig(new MapConfig()
//                        .setName("userCache")
//                        .setEvictionConfig(new EvictionConfig().setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
//                                .setEvictionPolicy(EvictionPolicy.LRU) // Adjust eviction policy if needed
//                                .setSize(200)) // Adjust size based on your need
//                        .setTimeToLiveSeconds(2000));
//    }
//
//
//}

@Configuration
@EnableScheduling
public class CacheConfig {

    @Bean
    public Config configure() {
        Config config = new Config()
                .setInstanceName("hazelcast-instance");

        // User Cache Configuration
        config.addMapConfig(new MapConfig()
                .setName("userCache")
                .setEvictionConfig(new EvictionConfig().setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
                        .setEvictionPolicy(EvictionPolicy.LRU)
                        .setSize(200))
                .setTimeToLiveSeconds(60));

        // Student Cache Configuration
        config.addMapConfig(new MapConfig()
                .setName("studentCache")
                .setEvictionConfig(new EvictionConfig().setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
                        .setEvictionPolicy(EvictionPolicy.LRU)
                        .setSize(500))
                .setTimeToLiveSeconds(20));

        return config;
    }
}

