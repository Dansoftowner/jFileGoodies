package com.jfilegoodies.explorer;

import java.io.File;

import static com.jfilegoodies.util.StringLiterals.*;

/**
 * A {@link MacFileExplorer} is a {@link FileExplorer} implementation
 * that works on a MacOS operating system.
 *
 * @author Daniel Gyorffy
 */
public class MacFileExplorer extends FileExplorer {

    private static final String OPEN = "open";

    @Override
    protected String createOpenCommand() {
        return OPEN + SPACE + TILDE;
    }

    @Override
    protected String createOpenDirCommand(File file) {
        return new StringBuilder()
                .append(OPEN)
                .append(SPACE)
                .append(QUOTATION)
                .append(file.getAbsoluteFile().getAbsolutePath())
                .append(QUOTATION)
                .toString();
    }

    /**
     * Based on <a href="https://stackoverflow.com/questions/39214539/opening-finder-from-terminal-with-file-selected">this</a>
     */
    @Override
    protected String createOpenSelectCommand(File file) {
        return new StringBuilder()
                .append(OPEN)
                .append(SPACE)
                .append("-a")
                .append(SPACE)
                .append("Finder")
                .append(SPACE)
                .append(QUOTATION)
                .append(file.getAbsoluteFile().getAbsolutePath())
                .append(QUOTATION)
                .toString();
    }
}
