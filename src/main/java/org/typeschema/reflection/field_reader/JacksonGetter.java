package org.typeschema.reflection.field_reader;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.typeschema.reflection.Field;
import org.typeschema.reflection.FieldReaderInterface;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JacksonGetter implements FieldReaderInterface {

    @Override
    public List<Field> read(Class<?> source) {
        var result = new ArrayList<Field>();
        var methods = source.getMethods();

        for (Method method : methods) {
            if (method.getDeclaringClass() != source) {
                continue;
            }

            var getterAnnotation = method.getAnnotation(JsonGetter.class);
            if (getterAnnotation == null) {
                continue;
            }

            result.add(new Field(getterAnnotation.value(), method.getReturnType(), method.getGenericReturnType()));
        }

        return result;
    }

}
