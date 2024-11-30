
# About

This library provides a fully level 5 compliant reflector class to automatically build a [TypeSchema](https://typeschema.org/)
specification based on a class using reflection. Through this you can easily transform your DTO classes into
a TypeSchema specification.

Since the TypeSchema specification contains all information of each DTO you can transform the spec again back into DTO
classes or also any other output which is supported by TypeSchema. This can help to re-use your DTO classes in different
environments or also to generate other specifications.

Level 5 compliant means that this library supports all features of TypeSchema and you can transform
your Java-Class into a TypeSchema specification and then use the TypeSchema specification to generate
a Java-Class which can be again turned into a TypeSchema specification without any information loss.

```
        Reflector       Generator       Reflector
            |               |               |
Java Class ---> TypeSchema ---> Java Class ---> TypeSchema
```

In this case the first TypeSchema specification is always equal to the last TypeSchema specification.

## Usage

To use this library you only need to create a `Reflector` instance which returns a `TypeSchema` model.
This model can then be transformed into a JSON string using the Jackson `ObjectMapper`.

```java
import org.typeschema.reflection.Reflector;

class App {

    public static void main(String[] args) {

        // build TypeSchema specification
        var reflector = new Reflector();
        var spec = reflector.build(Student.class);

        // serialize to json
        var objectMapper = (new ObjectMapper())
                .findAndRegisterModules()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);

        String json = objectMapper.writeValueAsString(spec);

    }

}
```

## Generator

We provide a hosted version of the code generator at our [website](https://typeschema.org/generator).
To transform a TypeSchema specification into code you can use our [generator](https://github.com/apioo/typeschema-generator)
[docker image](https://hub.docker.com/r/apiootech/typeschema-generator). Simply run `docker-compose up` which reads the `typeschema.json`
specification from the `output/` folder and writes the generated code back into this folder.

```
services:
  generator:
    image: apiootech/typeschema-generator:0.6
    environment:
      # possible formats: csharp, go, java, php, python, rust, typescript
      FORMAT: "java"
      NAMESPACE: "org.acme"
      SOURCE: "typeschema.json"
    volumes:
      - ./output:/usr/src/typeschema/output
```

For more advanced integration options please take a look at the [SDKgen](https://sdkgen.app/) project
which offers various integration options like a CLI, GitHub action or REST API.

## Tests

For more examples you can take a look at the [ReflectorTest](./src/test/java/org/typeschema/reflection/ReflectorTest.java)
which implements all level 5 tests of the TypeSchema specification.
