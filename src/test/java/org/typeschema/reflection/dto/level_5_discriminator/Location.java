package org.typeschema.reflection.dto.level_5_discriminator;

import com.fasterxml.jackson.annotation.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Web.class, name = "web"),
    @JsonSubTypes.Type(value = World.class, name = "world"),
})
public abstract class Location {
    private String type;

    @JsonSetter("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonGetter("type")
    public String getType() {
        return this.type;
    }
}

