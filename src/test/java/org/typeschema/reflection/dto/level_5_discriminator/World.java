package org.typeschema.reflection.dto.level_5_discriminator;

import com.fasterxml.jackson.annotation.*;

public class World extends Location {
    private String lat;
    private String _long;

    @JsonSetter("lat")
    public void setLat(String lat) {
        this.lat = lat;
    }

    @JsonGetter("lat")
    public String getLat() {
        return this.lat;
    }

    @JsonSetter("long")
    public void setLong(String _long) {
        this._long = _long;
    }

    @JsonGetter("long")
    public String getLong() {
        return this._long;
    }
}

