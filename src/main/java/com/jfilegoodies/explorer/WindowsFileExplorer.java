package com.jfilegoodies.explorer;

import java.io.File;
import java.io.IOException;

public class WindowsFileExplorer extends FileExplorer {

    private static final String SPACE = " ";

    @Override
    public void show(File file) throws IOException {
        super.show(file);
        StringBuilder commandBuilder = new StringBuilder()
                .append("explorer.exe")
                .append(SPACE)
                .append("/select, ")
                .append("\"")
                .append(file.getAbsoluteFile().getAbsolutePath())
                .append("\"");

        Process process = Runtime.getRuntime().exec(commandBuilder.toString());
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
