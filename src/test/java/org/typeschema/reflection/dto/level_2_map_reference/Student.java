package org.typeschema.reflection.dto.level_2_map_reference;

import com.fasterxml.jackson.annotation.*;

public class Student {
    private String firstName;
    private String lastName;
    private Integer age;
    private StudentMapReference properties;

    @JsonSetter("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonGetter("firstName")
    public String getFirstName() {
        return this.firstName;
    }

    @JsonSetter("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonGetter("lastName")
    public String getLastName() {
        return this.lastName;
    }

    @JsonSetter("age")
    public void setAge(Integer age) {
        this.age = age;
    }

    @JsonGetter("age")
    public Integer getAge() {
        return this.age;
    }

    @JsonSetter("properties")
    public void setProperties(StudentMapReference properties) {
        this.properties = properties;
    }

    @JsonGetter("properties")
    public StudentMapReference getProperties() {
        return this.properties;
    }
}

