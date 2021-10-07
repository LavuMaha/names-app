package com.galvanize;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="names")
public class NewName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public NewName(){}

    public NewName(String name) {
        this.name = name;
    }


    public NewName(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewName newName = (NewName) o;
        return id == newName.id &&
                Objects.equals(name, newName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
