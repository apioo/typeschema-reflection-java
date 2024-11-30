package org.typeschema.reflection.dto.level_5_discriminator;

import com.fasterxml.jackson.annotation.*;

public class Human {
    private String firstName;
    private String lastName;
    private Location location;

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

    @JsonSetter("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonGetter("location")
    public Location getLocation() {
        return this.location;
    }
}

