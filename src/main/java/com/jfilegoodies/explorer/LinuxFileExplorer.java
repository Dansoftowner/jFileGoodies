package com.jfilegoodies.explorer;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.jfilegoodies.util.StringLiterals.QUOTATION;
import static com.jfilegoodies.util.StringLiterals.SPACE;

/**
 * A {@link LinuxFileExplorer} is a {@link FileExplorer} implementation
 * that works on a Linux operating system.
 *
 * <p>
 * Some functions may not work if the 'nautilus' command is not available on the system.
 * It can be fixed with: <i>sudo apt install nautilus</i>
 *
 * @author Daniel Gyorffy
 */
public class LinuxFileExplorer extends FileExplorer {

    private static final String NAUTILUS = "nautilus";
    private static final String XDG_OPEN = "xdg-open";

    @Override
    public boolean open() throws IOException {
        Process process = Runtime.getRuntime().exec(new String[] {NAUTILUS, "--browser"});

        try {
            process.waitFor(1, TimeUnit.SECONDS);
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

        return executeCommand(XDG_OPEN, file);
    }

    @Override
    public boolean openSelect(File file) throws IOException {
        if (file == null || !file.exists()) {
            return false;
        }

        return executeCommand(NAUTILUS, file);
    }

    private boolean executeCommand(String command, File file) throws IOException {
        StringBuilder commandBuilder = new StringBuilder()
                .append(command)
                .append(SPACE)
                .append(QUOTATION)
                .append(file.getAbsoluteFile().getAbsolutePath())
                .append(QUOTATION);

        Process process = Runtime.getRuntime().exec(commandBuilder.toString());

        try {
            process.waitFor(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
