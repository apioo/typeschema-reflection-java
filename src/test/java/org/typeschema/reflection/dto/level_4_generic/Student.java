package org.typeschema.reflection.dto.level_4_generic;

import com.fasterxml.jackson.annotation.*;

public class Student {
    private Integer matricleNumber;

    @JsonSetter("matricleNumber")
    public void setMatricleNumber(Integer matricleNumber) {
        this.matricleNumber = matricleNumber;
    }

    @JsonGetter("matricleNumber")
    public Integer getMatricleNumber() {
        return this.matricleNumber;
    }
}

