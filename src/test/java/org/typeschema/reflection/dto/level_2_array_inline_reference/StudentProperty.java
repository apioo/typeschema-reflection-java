package org.typeschema.reflection.dto.level_2_array_inline_reference;

import com.fasterxml.jackson.annotation.*;

public class StudentProperty {
    private String name;
    private String value;

    @JsonSetter("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("name")
    public String getName() {
        return this.name;
    }

    @JsonSetter("value")
    public void setValue(String value) {
        this.value = value;
    }

    @JsonGetter("value")
    public String getValue() {
        return this.value;
    }
}

