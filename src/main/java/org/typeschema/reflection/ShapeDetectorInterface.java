package org.typeschema.reflection;

public interface ShapeDetectorInterface {

    boolean isMapShape(Class<?> type);

    boolean isArrayShape(Class<?> type);

    boolean isStringShape(Class<?> type);

    boolean isBooleanShape(Class<?> type);

    boolean isIntegerShape(Class<?> type);

    boolean isNumberShape(Class<?> type);

    boolean isDateShape(Class<?> type);

    boolean isDateTimeShape(Class<?> type);

    boolean isTimeShape(Class<?> type);

    boolean isAnyShape(Class<?> type);

}
