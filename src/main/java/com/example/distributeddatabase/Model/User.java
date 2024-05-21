package com.example.distributeddatabase.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class User implements Portable {

    /**
     *
     */
    public static final int CLASS_ID = 1;  // Unique ID for the User class
    public static final int FACTORY_ID = 3; // Use the same factory ID as in PortableFactory

    //    private static final long serialVersionUID = -6912201477750422475L;
    @Id
    private int id;
    private String name;
    private String address;

    @Override
    @JsonIgnore
    public int getFactoryId() {
        return FACTORY_ID;
    }
    @JsonIgnore
    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void writePortable(PortableWriter writer) throws IOException {
        writer.writeInt("id", id);
        writer.writeUTF("name", name);
        writer.writeUTF("address", address);
    }

    @Override
    public void readPortable(PortableReader reader) throws IOException {
        id = reader.readInt("id");
        name = reader.readUTF("name");
        address = reader.readUTF("address");
    }
}