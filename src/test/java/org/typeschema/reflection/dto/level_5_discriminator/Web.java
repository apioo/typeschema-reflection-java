package org.typeschema.reflection.dto.level_5_discriminator;

import com.fasterxml.jackson.annotation.*;

public class Web extends Location {
    private String url;

    @JsonSetter("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonGetter("url")
    public String getUrl() {
        return this.url;
    }
}

