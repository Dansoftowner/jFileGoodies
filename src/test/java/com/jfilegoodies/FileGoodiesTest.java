package com.jfilegoodies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class FileGoodiesTest {

    @Test
    public void testIsOSExecutable() {
        FileGoodies.getExecutableExtensions().stream()
                .map(extension -> new File(Math.random() + "." + extension))
                .map(FileGoodies::isOSExecutable)
                .forEach(Assertions::assertTrue);

        Stream.of("txt", "mp4", "jpeg", "docx", "xls", "db")
                .map(extension -> new File(Math.random() + "." + extension))
                .map(FileGoodies::isOSExecutable)
                .forEach(Assertions::assertFalse);
    }

    @Test
    public void testHasValidPath() {
        assertTrue(FileGoodies.hasValidPath(new File("./test.txt")));
        assertFalse(FileGoodies.hasValidPath(new File(",/tx?@&t< > ^")));
    }

    @Test
    public void testShortenedFilePath() {
        File file = new File("home/users/example/documents/MyFile.txt");
        assertEquals("home/users/example/documents/MyFile.txt", FileGoodies.shortenedFilePath(file, "...", '/', 4));
        assertEquals(".../users/example/documents/MyFile.txt", FileGoodies.shortenedFilePath(file, "...", '/', 3));
        assertEquals("<...>/example/documents/MyFile.txt", FileGoodies.shortenedFilePath(file, "<...>", '/', 2));
        assertEquals(".|documents|MyFile.txt", FileGoodies.shortenedFilePath(file, ".", '|', 1));
        assertEquals(".../MyFile.txt", FileGoodies.shortenedFilePath(file, "...", '/', 0));
    }

    @Test
    public void testDeprecateFile() {
       File original = new File("path/to/theFile.log");
       File deprecated = FileGoodies.deprecateFile(original);
       assertNotEquals(deprecated, original);
       assertTrue(deprecated.getName().matches("theFile_old\\d{4}\\.log"));
    }
}
