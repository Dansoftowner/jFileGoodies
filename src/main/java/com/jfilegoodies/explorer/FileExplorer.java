package com.jfilegoodies.explorer;

import java.io.File;
import java.io.IOException;

/**
 * A FileExplorer represents the GUI file-explorer provided by the OS.
 *
 * <p>
 * The {@link FileExplorer} itself is an abstract structure because file-explorers
 * work differently on each OS.
 * <p>
 * You can use {@link FileExplorers#get()} to create the right implementation
 * that works on the current operating system.
 *
 * @see FileExplorers
 * @author Daniel Gyorffy
 */
public abstract class FileExplorer {

    /**
     * Opens the gui file-explorer.
     *
     * <p>
     * Note:
     * <i>On Linux, 'nautilus' should be installed on the system (if it's not by default) <span color='red'>sudo apt install nautilus</span></i>
     *
     * @return {@code true} if the window opened; {@code false} otherwise
     * @throws IOException if some I/O exception occurs
     */
    public abstract boolean open() throws IOException;

    /**
     * Opens the gui file-explorer window where the specified directory is opened.
     *
     * <p>
     * If the specified {@link File} doesn't exist or it is not a directory
     * then it will immediately return with {@code false}.
     *
     * @param file the file object that represents the directory; may be null (then the method will immediately return)
     * @return {@code true} if the window is opened; {@code false} otherwise.
     * @throws IOException if some I/O exception occurs
     */
    public abstract boolean openDir(File file) throws IOException;

    /**
     * Opens the gui file-explorer window where the specified file is selected.
     *
     * <p>
     * If the file doesn't exist then it will immediately return with {@code false}.
     * <p>
     * Note:
     * <i>On Linux, 'nautilus' should be installed on the system (if it's not by default) <span color='red'>sudo apt install nautilus</span></i>
     *
     * @param file the file object that represents the file or directory; may be null (then the method will immediately return)
     * @return {@code true} if the window is opened; {@code false} otherwise
     * @throws IOException if some I/O exception occurs
     */
    public abstract boolean openSelect(File file) throws IOException;
}
