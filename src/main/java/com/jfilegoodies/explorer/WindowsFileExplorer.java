package com.jfilegoodies.explorer;

import java.io.File;

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
    protected String createOpenCommand() {
        return EXPLORER_EXE;
    }

    @Override
    protected String createOpenDirCommand(File file) {
        return new StringBuilder()
                .append(EXPLORER_EXE)
                .append(SPACE)
                .append("/root,")
                .append(SPACE)
                .append(QUOTATION)
                .append(file.getAbsoluteFile().getAbsolutePath())
                .append(QUOTATION)
                .toString();
    }

    @Override
    protected String createOpenSelectCommand(File file) {
        return new StringBuilder()
                .append(EXPLORER_EXE)
                .append(SPACE)
                .append("/select,")
                .append(SPACE)
                .append(QUOTATION)
                .append(file.getAbsoluteFile().getAbsolutePath())
                .append(QUOTATION)
                .toString();
    }
}
