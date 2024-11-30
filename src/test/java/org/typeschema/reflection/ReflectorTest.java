package org.typeschema.reflection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ReflectorTest {

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = (new ObjectMapper())
            .findAndRegisterModules()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Test
    public void testLevel1Format() throws ReflectorException, IOException, URISyntaxException {
        var spec = (new Reflector()).build(org.typeschema.reflection.dto.level_1_format.Student.class);

        String actual = objectMapper.writeValueAsString(spec);
        String expect = Files.readString(Path.of(Objects.requireNonNull(ReflectorTest.class.getClassLoader().getResource("level_1_format.json")).toURI()));

        Assert.assertEquals(objectMapper.readTree(expect), objectMapper.readTree(actual));
    }

    @Test
    public void testLevel1Simple() throws ReflectorException, IOException, URISyntaxException {
        var spec = (new Reflector()).build(org.typeschema.reflection.dto.level_1_simple.Student.class);

        String actual = objectMapper.writeValueAsString(spec);
        String expect = Files.readString(Path.of(Objects.requireNonNull(ReflectorTest.class.getClassLoader().getResource("level_1_simple.json")).toURI()));

        Assert.assertEquals(objectMapper.readTree(expect), objectMapper.readTree(actual));
    }

    @Test
    public void testLevel2ArrayInlineReference() throws ReflectorException, IOException, URISyntaxException {
        var spec = (new Reflector()).build(org.typeschema.reflection.dto.level_2_array_inline_reference.Student.class);

        String actual = objectMapper.writeValueAsString(spec);
        String expect = Files.readString(Path.of(Objects.requireNonNull(ReflectorTest.class.getClassLoader().getResource("level_2_array_inline_reference.json")).toURI()));

        Assert.assertEquals(objectMapper.readTree(expect), objectMapper.readTree(actual));
    }

    @Test
    public void testLevel2ArrayInlineString() throws ReflectorException, IOException, URISyntaxException {
        var spec = (new Reflector()).build(org.typeschema.reflection.dto.level_2_array_inline_string.Student.class);

        String actual = objectMapper.writeValueAsString(spec);
        String expect = Files.readString(Path.of(Objects.requireNonNull(ReflectorTest.class.getClassLoader().getResource("level_2_array_inline_string.json")).toURI()));

        Assert.assertEquals(objectMapper.readTree(expect), objectMapper.readTree(actual));
    }

    @Test
    public void testLevel2ArrayReference() throws ReflectorException, IOException, URISyntaxException {
        var spec = (new Reflector()).build(org.typeschema.reflection.dto.level_2_array_reference.Student.class);

        String actual = objectMapper.writeValueAsString(spec);
        String expect = Files.readString(Path.of(Objects.requireNonNull(ReflectorTest.class.getClassLoader().getResource("level_2_array_reference.json")).toURI()));

        Assert.assertEquals(objectMapper.readTree(expect), objectMapper.readTree(actual));
    }

    @Test
    public void testLevel2ArrayString() throws ReflectorException, IOException, URISyntaxException {
        var spec = (new Reflector()).build(org.typeschema.reflection.dto.level_2_array_string.Student.class);

        String actual = objectMapper.writeValueAsString(spec);
        String expect = Files.readString(Path.of(Objects.requireNonNull(ReflectorTest.class.getClassLoader().getResource("level_2_array_string.json")).toURI()));

        Assert.assertEquals(objectMapper.readTree(expect), objectMapper.readTree(actual));
    }

    @Test
    public void testLevel2MapInlineReference() throws ReflectorException, IOException, URISyntaxException {
        var spec = (new Reflector()).build(org.typeschema.reflection.dto.level_2_map_inline_reference.Student.class);

        String actual = objectMapper.writeValueAsString(spec);
        String expect = Files.readString(Path.of(Objects.requireNonNull(ReflectorTest.class.getClassLoader().getResource("level_2_map_inline_reference.json")).toURI()));

        Assert.assertEquals(objectMapper.readTree(expect), objectMapper.readTree(actual));
    }

    @Test
    public void testLevel2MapInlineString() throws ReflectorException, IOException, URISyntaxException {
        var spec = (new Reflector()).build(org.typeschema.reflection.dto.level_2_map_inline_string.Student.class);

        String actual = objectMapper.writeValueAsString(spec);
        String expect = Files.readString(Path.of(Objects.requireNonNull(ReflectorTest.class.getClassLoader().getResource("level_2_map_inline_string.json")).toURI()));

        Assert.assertEquals(objectMapper.readTree(expect), objectMapper.readTree(actual));
    }

    @Test
    public void testLevel2MapReference() throws ReflectorException, IOException, URISyntaxException {
        var spec = (new Reflector()).build(org.typeschema.reflection.dto.level_2_map_reference.Student.class);

        String actual = objectMapper.writeValueAsString(spec);
        String expect = Files.readString(Path.of(Objects.requireNonNull(ReflectorTest.class.getClassLoader().getResource("level_2_map_reference.json")).toURI()));

        Assert.assertEquals(objectMapper.readTree(expect), objectMapper.readTree(actual));
    }

    @Test
    public void testLevel2MapString() throws ReflectorException, IOException, URISyntaxException {
        var spec = (new Reflector()).build(org.typeschema.reflection.dto.level_2_map_string.Student.class);

        String actual = objectMapper.writeValueAsString(spec);
        String expect = Files.readString(Path.of(Objects.requireNonNull(ReflectorTest.class.getClassLoader().getResource("level_2_map_string.json")).toURI()));

        Assert.assertEquals(objectMapper.readTree(expect), objectMapper.readTree(actual));
    }

    @Test
    public void testLevel3Inheritance() throws ReflectorException, IOException, URISyntaxException {
        var spec = (new Reflector()).build(org.typeschema.reflection.dto.level_3_inheritance.Student.class);

        String actual = objectMapper.writeValueAsString(spec);
        String expect = Files.readString(Path.of(Objects.requireNonNull(ReflectorTest.class.getClassLoader().getResource("level_3_inheritance.json")).toURI()));

        Assert.assertEquals(objectMapper.readTree(expect), objectMapper.readTree(actual));
    }

    @Test
    public void testLevel4Generic() throws ReflectorException, IOException, URISyntaxException {
        var spec = (new Reflector()).build(org.typeschema.reflection.dto.level_4_generic.StudentMap.class);

        String actual = objectMapper.writeValueAsString(spec);
        String expect = Files.readString(Path.of(Objects.requireNonNull(ReflectorTest.class.getClassLoader().getResource("level_4_generic.json")).toURI()));

        Assert.assertEquals(objectMapper.readTree(expect), objectMapper.readTree(actual));
    }

    @Test
    public void testLevel5Discriminator() throws ReflectorException, IOException, URISyntaxException {
        var spec = (new Reflector()).build(org.typeschema.reflection.dto.level_5_discriminator.Human.class);

        String actual = objectMapper.writeValueAsString(spec);
        String expect = Files.readString(Path.of(Objects.requireNonNull(ReflectorTest.class.getClassLoader().getResource("level_5_discriminator.json")).toURI()));

        Assert.assertEquals(objectMapper.readTree(expect), objectMapper.readTree(actual));
    }

}
