package org.typeschema.reflection.dto.level_1_simple;

import com.fasterxml.jackson.annotation.*;

public class Faculty {
    private String name;

    @JsonSetter("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("name")
    public String getName() {
        return this.name;
    }
}

