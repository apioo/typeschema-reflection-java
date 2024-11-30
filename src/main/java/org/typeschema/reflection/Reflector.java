package org.typeschema.reflection;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.typeschema.model.*;
import org.typeschema.reflection.field_reader.JacksonGetter;
import org.typeschema.reflection.shape_detector.BasicDetector;

import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reflector implements ReflectorInterface {

    private final FieldReaderInterface fieldReader;
    private final ShapeDetectorInterface shapeDetector;

    public Reflector(FieldReaderInterface fieldReader, ShapeDetectorInterface shapeDetector) {
        this.fieldReader = fieldReader;
        this.shapeDetector = shapeDetector;
    }

    public Reflector() {
        this(new JacksonGetter(), new BasicDetector());
    }

    public TypeSchema build(Class<?> source) throws ReflectorException {
        try {
            Map<String, DefinitionType> definitions = new HashMap<>();

            var spec = new TypeSchema();
            var root = this.buildClass(source, definitions);

            spec.setDefinitions(definitions);
            spec.setRoot(root);

            return spec;
        } catch (ClassNotFoundException e) {
            throw new ReflectorException("Could not build TypeSchema for: " + source.getName(), e);
        }
    }

    public TypeSchema build(List<Class<?>> sources, String root) throws ReflectorException {
        Map<String, DefinitionType> definitions = new HashMap<>();

        var spec = new TypeSchema();

        for (var source : sources) {
            try {
                if (root == null) {
                    root = this.buildClass(source, definitions);
                } else {
                    this.buildClass(source, definitions);
                }
            } catch (ClassNotFoundException e) {
                throw new ReflectorException("Could not build TypeSchema for: " + source.getName(), e);
            }
        }

        spec.setDefinitions(definitions);
        spec.setRoot(root);

        return spec;
    }

    public TypeSchema build(List<Class<?>> sources) throws ReflectorException {
        return this.build(sources, null);
    }

    private String buildClass(Class<?> source, Map<String, DefinitionType> definitions) throws ClassNotFoundException, ReflectorException {
        var name = this.buildName(source);
        if (definitions.containsKey(name)) {
            return name;
        }

        String description = null;
        var descriptionAnnotation = source.getAnnotation(JsonClassDescription.class);
        if (descriptionAnnotation != null) {
            description = descriptionAnnotation.value();
        }

        Boolean deprecated = null;
        var deprecatedAnnotation = source.getAnnotation(Deprecated.class);
        if (deprecatedAnnotation != null) {
            deprecated = deprecatedAnnotation.forRemoval();
        }

        var parent = source.getSuperclass();
        var parentGeneric = source.getGenericSuperclass();
        if (this.shapeDetector.isMapShape(parent)) {
            var map = new MapDefinitionType();
            map.setType("map");
            map.setDescription(description);
            map.setDeprecated(deprecated);

            definitions.put(name, map);

            map.setSchema(this.buildMapSchema(parentGeneric, definitions));
        } else if (this.shapeDetector.isArrayShape(parent)) {
            var array = new ArrayDefinitionType();
            array.setType("array");
            array.setDescription(description);
            array.setDeprecated(deprecated);

            definitions.put(name, array);

            array.setSchema(this.buildArraySchema(parentGeneric, definitions));
        } else {
            var struct = new StructDefinitionType();
            struct.setType("struct");
            struct.setDescription(description);
            struct.setDeprecated(deprecated);

            definitions.put(name, struct);

            if (parent != null && !parent.getTypeName().startsWith("java.")) {
                var parentName = this.buildClass(parent, definitions);
                if (parentGeneric instanceof ParameterizedType) {
                    struct.setParent(this.buildReference(parentName, (ParameterizedType) parentGeneric, definitions));
                } else {
                    struct.setParent(this.buildReference(parentName, definitions));
                }
            }

            if (Modifier.isAbstract(source.getModifiers())) {
                struct.setBase(true);
            }

            var properties = new HashMap<String, PropertyType>();
            var fields = this.fieldReader.read(source);
            for (Field field : fields) {
                properties.put(field.name(), this.buildProperty(field.type(), field.genericType(), definitions));
            }
            struct.setProperties(properties);

            var typeInfo = source.getAnnotation(JsonTypeInfo.class);
            var subTypes = source.getAnnotation(JsonSubTypes.class);
            if (typeInfo != null && subTypes != null) {
                struct.setDiscriminator(typeInfo.property());

                Map<String, String> mapping = new HashMap<>();
                for (var subType : subTypes.value()) {
                    mapping.put(this.buildClass(subType.value(), definitions), subType.name());
                }
                struct.setMapping(mapping);
            }
        }

        return name;
    }

    private PropertyType buildProperty(Class<?> type, Type genericType, Map<String, DefinitionType> definitions) throws ClassNotFoundException, ReflectorException {
        PropertyType result;

        String description = null;
        var descriptionAnnotation = type.getAnnotation(JsonClassDescription.class);
        if (descriptionAnnotation != null) {
            description = descriptionAnnotation.value();
        }

        Boolean deprecated = null;
        var deprecatedAnnotation = type.getAnnotation(Deprecated.class);
        if (deprecatedAnnotation != null) {
            deprecated = deprecatedAnnotation.forRemoval();
        }

        if (this.shapeDetector.isStringShape(type)) {
            var string = new StringPropertyType();
            string.setType("string");
            string.setDescription(description);
            string.setDeprecated(deprecated);

            result = string;
        } else if (this.shapeDetector.isBooleanShape(type)) {
            var bool =  new BooleanPropertyType();
            bool.setType("boolean");
            bool.setDescription(description);
            bool.setDeprecated(deprecated);

            result = bool;
        } else if (this.shapeDetector.isIntegerShape(type)) {
            var integer = new IntegerPropertyType();
            integer.setType("integer");
            integer.setDescription(description);
            integer.setDeprecated(deprecated);

            result = integer;
        } else if (this.shapeDetector.isNumberShape(type)) {
            var number = new NumberPropertyType();
            number.setType("number");
            number.setDescription(description);
            number.setDeprecated(deprecated);

            result = number;
        } else if (this.shapeDetector.isAnyShape(type)) {
            var any = new AnyPropertyType();
            any.setType("any");
            any.setDescription(description);
            any.setDeprecated(deprecated);

            result = any;
        } else if (this.shapeDetector.isDateShape(type)) {
            var string = new StringPropertyType();
            string.setType("string");
            string.setDescription(description);
            string.setDeprecated(deprecated);
            string.setFormat("date");

            result = string;
        } else if (this.shapeDetector.isDateTimeShape(type)) {
            var string = new StringPropertyType();
            string.setType("string");
            string.setDescription(description);
            string.setDeprecated(deprecated);
            string.setFormat("date-time");

            result = string;
        } else if (this.shapeDetector.isTimeShape(type)) {
            var string = new StringPropertyType();
            string.setType("string");
            string.setDescription(description);
            string.setDeprecated(deprecated);
            string.setFormat("time");

            result = string;
        } else if (this.shapeDetector.isMapShape(type) && genericType != null) {
            var map = new MapPropertyType();
            map.setType("map");
            map.setDescription(description);
            map.setDeprecated(deprecated);
            map.setSchema(this.buildMapSchema(genericType, definitions));

            result = map;
        } else if (this.shapeDetector.isArrayShape(type) && genericType != null) {
            var array = new ArrayPropertyType();
            array.setType("array");
            array.setDescription(description);
            array.setDeprecated(deprecated);
            array.setSchema(this.buildArraySchema(genericType, definitions));

            result = array;
        } else if (type.isArray()) {
            var array = new ArrayPropertyType();
            array.setType("array");
            array.setDescription(description);
            array.setDeprecated(deprecated);
            array.setSchema(this.buildProperty(type.getComponentType(), null, definitions));

            result = array;
        } else {
            var target = this.buildClass(type, definitions);

            result = this.buildReference(target, definitions);
        }

        return result;
    }

    private String buildName(Class<?> source) {
        return source.getSimpleName();
    }

    private GenericPropertyType buildGeneric(String name) {
        var result = new GenericPropertyType();
        result.setType("generic");
        result.setName(name);

        return result;
    }

    private ReferencePropertyType buildReference(String target, ParameterizedType type, Map<String, DefinitionType> definitions) throws ClassNotFoundException, ReflectorException {
        var result = new ReferencePropertyType();
        result.setType("reference");
        result.setTarget(target);

        if (type != null) {
            var arguments = type.getActualTypeArguments();
            var rawType = type.getRawType();
            if (arguments.length > 0 && rawType instanceof Class<?>) {
                var typeParameters = ((Class<?>) rawType).getTypeParameters();
                if (typeParameters.length == arguments.length) {
                    var template = new HashMap<String, String>();
                    for (var i = 0; i < arguments.length; i++) {
                        if (arguments[i] instanceof Class<?>) {
                            template.put(typeParameters[i].getName(), this.buildClass((Class<?>) arguments[i], definitions));
                        }
                    }

                    if (!template.isEmpty()) {
                        result.setTemplate(template);
                    }
                }
            }
        }

        return result;
    }

    private ReferencePropertyType buildReference(String target, Map<String, DefinitionType> definitions) throws ClassNotFoundException, ReflectorException {
        return this.buildReference(target, null, definitions);
    }

    private PropertyType buildMapSchema(Type type, Map<String, DefinitionType> definitions) throws ClassNotFoundException, ReflectorException {
        if (type instanceof ParameterizedType) {
            var arguments = ((ParameterizedType) type).getActualTypeArguments();
            // arguments[0] is the key which we dont need
            var value = arguments[1];

            if (value instanceof Class<?>) {
                return this.buildProperty((Class<?>) value, null, definitions);
            } else if (value instanceof TypeVariable) {
                return this.buildGeneric(((TypeVariable<?>) value).getName());
            }
        }

        throw new ReflectorException("Provided an invalid type: " + type.getTypeName());
    }

    private PropertyType buildArraySchema(Type type, Map<String, DefinitionType> definitions) throws ClassNotFoundException, ReflectorException {
        if (type instanceof ParameterizedType) {
            var arguments = ((ParameterizedType) type).getActualTypeArguments();
            var value = arguments[0];

            if (value instanceof Class<?>) {
                return this.buildProperty((Class<?>) value, null, definitions);
            } else if (value instanceof TypeVariable) {
                return this.buildGeneric(((TypeVariable<?>) value).getName());
            }
        }

        throw new ReflectorException("Provided an invalid type: " + type.getTypeName());
    }

}
