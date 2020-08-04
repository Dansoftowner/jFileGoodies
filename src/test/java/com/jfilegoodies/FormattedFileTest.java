package com.jfilegoodies;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class FormattedFileTest {


    @Test
    public void testGetExtension() {
        FormattedFile formattedFile = new FormattedFile(new File("path/to/TextFile.txt"));
        assertEquals("txt", formattedFile.getExtension());
    }

    @Test
    public void testGetSimpleName() {
        FormattedFile formattedFile = new FormattedFile(new File("path/to/TextFile.txt"));
        assertEquals("TextFile", formattedFile.getSimpleName());
    }

    @Test
    public void testHasExtension() {
        FormattedFile formattedFile = new FormattedFile("path/to/MyFile");
        assertFalse(formattedFile.hasExtension());

        formattedFile = new FormattedFile("path/to/MyFile.docx");
        assertTrue(formattedFile.hasExtension());
    }
}
