package com.example.distributeddatabase;

import com.hazelcast.config.SerializationConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.nio.serialization.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@SpringBootApplication
@EnableCaching
public class DistributeddatabaseApplication  {

	public static void main(String[] args) {

		SpringApplication.run(DistributeddatabaseApplication.class, args);


		HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();


		Map<Integer, String> map = hazelcastInstance.getMap("userCache");
		map.put(3, "danu");
		map.put(4, "seshu");
		map.put(5, "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
	}

}



