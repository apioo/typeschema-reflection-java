package org.typeschema.reflection.dto.level_1_format;

import com.fasterxml.jackson.annotation.*;

public class Student {
    private String firstName;
    private String lastName;
    private java.time.LocalDate date;
    private java.time.LocalDateTime dateTime;
    private java.time.LocalTime time;

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

    @JsonSetter("date")
    public void setDate(java.time.LocalDate date) {
        this.date = date;
    }

    @JsonGetter("date")
    public java.time.LocalDate getDate() {
        return this.date;
    }

    @JsonSetter("dateTime")
    public void setDateTime(java.time.LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @JsonGetter("dateTime")
    public java.time.LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @JsonSetter("time")
    public void setTime(java.time.LocalTime time) {
        this.time = time;
    }

    @JsonGetter("time")
    public java.time.LocalTime getTime() {
        return this.time;
    }
}

