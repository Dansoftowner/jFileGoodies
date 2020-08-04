package com.jfilegoodies.explorer;

import java.io.File;
import java.io.IOException;

/**
 * A {@link WindowsFileExplorer} is a {@link FileExplorer} implementation
 * that works on a Microsoft Windows operating system.
 *
 * @author Daniel Gyorffy
 */
public class WindowsFileExplorer extends FileExplorer {

    private static final String EXPLORER_EXE = "explorer.exe";

    /**
     * The quotation character (")
     */
    private static final char QUOTATION = '"';

    /**
     * The space character
     */
    private static final char SPACE = ' ';

    @Override
    public boolean open() throws IOException {
        Process process = Runtime.getRuntime().exec(EXPLORER_EXE);

        try {
            process.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public boolean openDir(File file) throws IOException {
        if (file == null || !file.exists() ||!file.isDirectory()) {
            return false;
        }

        StringBuilder commandBuilder = new StringBuilder()
                .append(EXPLORER_EXE)
                .append(SPACE)
                .append("/root,")
                .append(SPACE)
                .append(QUOTATION)
                .append(file.getAbsoluteFile().getAbsolutePath())
                .append(QUOTATION);

        Process process = Runtime.getRuntime().exec(commandBuilder.toString());

        try {
            process.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public boolean openSelect(File file) throws IOException {
        if (file == null || !file.exists()) {
            return false;
        }

        StringBuilder commandBuilder = new StringBuilder()
                .append(EXPLORER_EXE)
                .append(SPACE)
                .append("/select,")
                .append(SPACE)
                .append(QUOTATION)
                .append(file.getAbsoluteFile().getAbsolutePath())
                .append(QUOTATION);

        Process process = Runtime.getRuntime().exec(commandBuilder.toString());

        try {
            process.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
