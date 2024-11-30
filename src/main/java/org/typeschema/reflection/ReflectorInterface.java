package org.typeschema.reflection;

import org.typeschema.model.TypeSchema;

import java.util.List;

public interface ReflectorInterface {

    /**
     * Builds a TypeSchema specification based on the provided class
     */
    TypeSchema build(Class<?> source) throws ReflectorException;

    /**
     * Builds a TypeSchema specification for multiple classes
     */
    TypeSchema build(List<Class<?>> sources) throws ReflectorException;
}
