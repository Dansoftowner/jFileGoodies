package com.jfilegoodies.explorer;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.jfilegoodies.util.StringLiterals.QUOTATION;
import static com.jfilegoodies.util.StringLiterals.SPACE;

/**
 * A {@link WindowsFileExplorer} is a {@link FileExplorer} implementation
 * that works on a Microsoft Windows operating system.
 *
 * @author Daniel Gyorffy
 */
public class WindowsFileExplorer extends FileExplorer {

    private static final String EXPLORER_EXE = "explorer.exe";

    @Override
    public boolean open() throws IOException {
        Process process = Runtime.getRuntime().exec(EXPLORER_EXE);

        try {
            process.waitFor(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public boolean openDir(File file) throws IOException {
        if (file == null || !file.exists() || !file.isDirectory()) {
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

        return executeCommand(commandBuilder.toString());
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

        return executeCommand(commandBuilder.toString());
    }

    private boolean executeCommand(String command) throws IOException {
        Process process = Runtime.getRuntime().exec(command);

        try {
            process.waitFor(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
