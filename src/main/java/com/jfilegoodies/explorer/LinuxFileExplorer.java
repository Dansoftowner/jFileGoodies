package com.jfilegoodies.explorer;

import java.io.File;

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
 * @since 1.0
 */
public class LinuxFileExplorer extends FileExplorer {

    private static final String NAUTILUS = "nautilus";
    private static final String XDG_OPEN = "xdg-open";

    @Override
    protected String createOpenCommand() {
        return NAUTILUS + SPACE + "--browser";
    }

    @Override
    protected String createOpenDirCommand(File file) {
        return new StringBuilder()
                .append(XDG_OPEN)
                .append(SPACE)
                .append(QUOTATION)
                .append(file.getAbsoluteFile().getAbsolutePath())
                .append(QUOTATION)
                .toString();
    }

    @Override
    protected String createOpenSelectCommand(File file) {
        return new StringBuilder()
                .append(NAUTILUS)
                .append(SPACE)
                .append(QUOTATION)
                .append(file.getAbsoluteFile().getAbsolutePath())
                .append(QUOTATION)
                .toString();
    }
}
