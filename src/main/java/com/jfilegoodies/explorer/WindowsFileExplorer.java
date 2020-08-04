package com.jfilegoodies.explorer;

import java.io.File;
import java.io.IOException;

public class WindowsFileExplorer extends FileExplorer {

    private static final String EXPLORER_EXE = "explorer.exe";

    private static final String QUOTATION = "\"";
    private static final String SPACE = " ";

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
