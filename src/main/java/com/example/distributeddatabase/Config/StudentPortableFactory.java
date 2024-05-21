package com.example.distributeddatabase.Config;

import com.example.distributeddatabase.Model.Student;
import com.example.distributeddatabase.Model.User;
import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableFactory;

public class StudentPortableFactory implements PortableFactory {
    public  static  final int FACTORY_ID=2;

    @Override
    public Portable create(int classId){
        if (classId== Student.CLASS_ID){
            return  new Student();
        }
        return  null;
    }
}