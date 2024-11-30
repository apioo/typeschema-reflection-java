package org.typeschema.reflection.dto.level_3_inheritance;

import com.fasterxml.jackson.annotation.*;

public class Student extends Human {
    private String studentId;

    @JsonSetter("studentId")
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @JsonGetter("studentId")
    public String getStudentId() {
        return this.studentId;
    }
}

