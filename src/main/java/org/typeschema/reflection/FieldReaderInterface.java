package org.typeschema.reflection;

import java.util.List;

public interface FieldReaderInterface {

    List<Field> read(Class<?> source);

}
