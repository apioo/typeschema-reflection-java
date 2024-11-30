package org.typeschema.reflection.shape_detector;

import org.typeschema.reflection.ShapeDetectorInterface;

public class BasicDetector implements ShapeDetectorInterface {

    public boolean isMapShape(Class<?> type) {
        return type != null && (type.getName().equals("java.util.Map") || type.getName().equals("java.util.HashMap"));
    }

    public boolean isArrayShape(Class<?> type) {
        return type != null && (type.getName().equals("java.util.List") || type.getName().equals("java.util.ArrayList"));
    }

    public boolean isStringShape(Class<?> type) {
        return type != null && type.getName().equals("java.lang.String");
    }

    public boolean isBooleanShape(Class<?> type) {
        return type != null && (type.getName().equals("boolean") || type.getName().equals("java.lang.Boolean"));
    }

    public boolean isIntegerShape(Class<?> type) {
        return type != null && (type.getName().equals("int") || type.getName().equals("long") || type.getName().equals("java.lang.Integer") || type.getName().equals("java.lang.Long"));
    }

    public boolean isNumberShape(Class<?> type) {
        return type != null && (type.getName().equals("float") || type.getName().equals("double") || type.getName().equals("java.lang.Float") || type.getName().equals("java.lang.Double"));
    }

    public boolean isDateShape(Class<?> type) {
        return type != null && (type.getName().equals("java.time.LocalDate"));
    }

    public boolean isDateTimeShape(Class<?> type) {
        return type != null && (type.getName().equals("java.time.LocalDateTime"));
    }

    public boolean isTimeShape(Class<?> type) {
        return type != null && (type.getName().equals("java.time.LocalTime"));
    }

    public boolean isAnyShape(Class<?> type) {
        return type != null && (type.getName().equals("java.lang.Object"));
    }

}
