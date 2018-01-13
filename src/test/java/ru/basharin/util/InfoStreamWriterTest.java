package ru.basharin.util;

import org.junit.Assert;
import org.junit.Test;
import ru.basharin.model.Rectangle;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InfoStreamWriterTest {
    @Test
    public void serialize() throws Exception {
        Path path = Files.createTempFile("Sides", "");
        List<Rectangle> rectangles = Arrays.asList(
                new Rectangle("A", "B", 6, 8),
                new Rectangle("D", "C", 5, 7),
                new Rectangle("E", "F", 3, 4)
        );

        try (OutputStream outputStream = Files.newOutputStream(path)) {
            InfoStreamWriter.serialize(rectangles, outputStream);
        }
        List<Rectangle> actual;
        try (InputStream inputStream = Files.newInputStream(path)) {
            actual = InfoStreamWriter.deSerialize(inputStream);
        }
        Assert.assertEquals(rectangles, actual);
    }

}