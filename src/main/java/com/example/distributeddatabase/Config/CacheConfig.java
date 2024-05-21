package com.example.distributeddatabase.Config;


import com.hazelcast.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
// This code is for run the instances with only one cache
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




////Run the instances with multiple caches
//@Configuration
//@EnableScheduling
//public class CacheConfig {
//
//    @Bean
//    public Config configure() {
//        Config config = new Config()
//                .setInstanceName("hazelcast-instance");
//
//
//////This block is for run multiple instances across multiple servers
////        NetworkConfig network = config.getNetworkConfig();
////
////        network.setPort(5801);
////        network.setPortAutoIncrement(true);
////
////        JoinConfig joinCfg = network.getJoin();
////
////        joinCfg.getMulticastConfig().setEnabled(true);
////        //joinCfg.getTcpIpConfig().addMember("10.45.67.32").addMember("10.45.67.100").setRequiredMember("192.168.10.100").setEnabled(true);
////
////        //network.getInterfaces().setEnabled(true).addInterface("10.45.67.*");
////
////
////        joinCfg.getMulticastConfig().setMulticastGroup("224.2.2.5");
////        joinCfg.getMulticastConfig().setMulticastPort(54320);
////
////
////        //
//
//
//        // User Cache Configuration
//        config.addMapConfig(new MapConfig()
//                .setName("userCache")
//                .setEvictionConfig(new EvictionConfig().setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
//                        .setEvictionPolicy(EvictionPolicy.LRU)
//                        .setSize(200))
//                .setTimeToLiveSeconds(2000));
//
//        // Student Cache Configuration
//        config.addMapConfig(new MapConfig()
//                .setName("studentCache")
//                .setEvictionConfig(new EvictionConfig().setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
//                        .setEvictionPolicy(EvictionPolicy.LRU)
//                        .setSize(500))
//                .setTimeToLiveSeconds(20));
//
//        return config;
//    }
//}
//









@Configuration
@EnableScheduling
public class CacheConfig {

    @Bean
    public Config configure() {
        Config config = new Config()
                .setInstanceName("hazelcast-instance");
        config.setClusterName("myCluster1");

//
//        // Multicast Discovery Configuration
//        JoinConfig joinConfig = config.getNetworkConfig().getJoin();
//        joinConfig.getMulticastConfig().setEnabled(true);
//
//
//        // (Optional) Set specific multicast group and port (defaults can be used)
//        joinConfig.getMulticastConfig().setMulticastGroup("224.2.2.3");
//        joinConfig.getMulticastConfig().setMulticastPort(54327);

        // TCP/IP Join Configuration (recommended for inter-machine communication)
        JoinConfig joinConfig = config.getNetworkConfig().getJoin();
        joinConfig.getTcpIpConfig().setEnabled(true);
        joinConfig.getTcpIpConfig()
                .addMember("192.168.8.119:5701") // Replace with IP/hostname:port of another machine
                .addMember("192.168.8.181:5701") // Replace with IP/hostname:port of another machine
                .addMember("192.168.8.119:5702") // Add more addresses as needed (different ports)
                .addMember("192.168.8.119:5703") ;// Replace with IP/hostname:port of another machine


        // (Optional) Disable multicast (if using TCP/IP join)
        joinConfig.getMulticastConfig().setEnabled(false); // Recommended for inter-machine
        // User Cache Configuration
        config.addMapConfig(new MapConfig()
                .setName("userCache")
                        .setStatisticsEnabled(true)
                        .setInMemoryFormat(InMemoryFormat.BINARY)
                .setEvictionConfig(new EvictionConfig()
                        .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
                        .setEvictionPolicy(EvictionPolicy.LRU)
                        .setSize(200))
                .setTimeToLiveSeconds(160));

        // Student Cache Configuration
        config.addMapConfig(new MapConfig()
                .setName("studentCache")
                .setStatisticsEnabled(true)

                .setEvictionConfig(new EvictionConfig()
                        .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
                        .setEvictionPolicy(EvictionPolicy.LRU)
                        .setSize(500))
                .setTimeToLiveSeconds(160));


        //register PortableFactory

        SerializationConfig serializationConfig=config.getSerializationConfig();
        serializationConfig.addPortableFactory(UserPortableFactory.FACTORY_ID,new UserPortableFactory());
        serializationConfig.addPortableFactory(StudentPortableFactory.FACTORY_ID,new StudentPortableFactory());


        return config;
    }
}
