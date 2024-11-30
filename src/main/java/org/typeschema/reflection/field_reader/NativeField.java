package org.typeschema.reflection.field_reader;

import org.typeschema.reflection.Field;
import org.typeschema.reflection.FieldReaderInterface;

import java.util.ArrayList;
import java.util.List;

public class NativeField implements FieldReaderInterface {

    @Override
    public List<Field> read(Class<?> source) {
        var result = new ArrayList<Field>();
        var fields = source.getDeclaredFields();

        for (java.lang.reflect.Field field : fields) {
            if (field.getDeclaringClass() != source) {
                continue;
            }

            result.add(new Field(field.getName(), field.getType(), field.getGenericType()));
        }

        return result;
    }

}
