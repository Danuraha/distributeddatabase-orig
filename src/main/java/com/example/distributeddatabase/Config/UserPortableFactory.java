package com.example.distributeddatabase.Config;

import com.example.distributeddatabase.Model.User;
import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableFactory;

public class UserPortableFactory implements PortableFactory {
    public  static  final int FACTORY_ID=3;

    @Override
    public Portable create(int classId){
        if (classId== User.CLASS_ID){
            return  new User();
        }
        return  null;
    }
}
