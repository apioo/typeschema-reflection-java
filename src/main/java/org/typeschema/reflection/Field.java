package org.typeschema.reflection;

import java.lang.reflect.Type;

public class Field {

    private final String name;
    private final Class<?> type;
    private final Type genericType;

    public Field(String name, Class<?> type, Type genericType) {
        this.name = name;
        this.type = type;
        this.genericType = genericType;
    }

    public Field(String name, Class<?> type) {
        this(name, type, null);
    }

    public String name() {
        return this.name;
    }

    public Class<?> type() {
        return this.type;
    }

    public Type genericType() {
        return this.genericType;
    }

}
