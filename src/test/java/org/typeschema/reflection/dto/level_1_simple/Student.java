package org.typeschema.reflection.dto.level_1_simple;

import com.fasterxml.jackson.annotation.*;

public class Student {
    private String firstName;
    private String lastName;
    private Integer age;
    private Boolean active;
    private Double score;
    private Faculty faculty;

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

    @JsonSetter("active")
    public void setActive(Boolean active) {
        this.active = active;
    }

    @JsonGetter("active")
    public Boolean getActive() {
        return this.active;
    }

    @JsonSetter("score")
    public void setScore(Double score) {
        this.score = score;
    }

    @JsonGetter("score")
    public Double getScore() {
        return this.score;
    }

    @JsonSetter("faculty")
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @JsonGetter("faculty")
    public Faculty getFaculty() {
        return this.faculty;
    }
}

