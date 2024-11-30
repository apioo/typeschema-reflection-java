package org.typeschema.reflection.dto.level_4_generic;

import com.fasterxml.jackson.annotation.*;

public class Map<T> {
    private Integer totalResults;
    private java.util.List<T> entries;

    @JsonSetter("totalResults")
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    @JsonGetter("totalResults")
    public Integer getTotalResults() {
        return this.totalResults;
    }

    @JsonSetter("entries")
    public void setEntries(java.util.List<T> entries) {
        this.entries = entries;
    }

    @JsonGetter("entries")
    public java.util.List<T> getEntries() {
        return this.entries;
    }
}

