/*
 * Copyright 2020 DansoftOwner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jfilegoodies.explorer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
 * @since 1.0
 */
public abstract class FileExplorer {

    private static final Logger logger = LoggerFactory.getLogger(FileExplorer.class);

    protected abstract String createOpenCommand();
    protected abstract String createOpenDirCommand(File file);
    protected abstract String createOpenSelectCommand(File file);

    /**
     * Opens the gui file-explorer.
     *
     * <p>
     * Note:
     * <i>On Linux, 'nautilus' should be installed on the system (if it's not by default) <span style='color:red'>sudo apt install nautilus</span></i>
     *
     * @return {@code true} if the window opened; {@code false} otherwise
     * @throws IOException if some I/O exception occurs
     * @since 1.0
     */
    public boolean open() throws IOException {
        return executeWithRuntime(createOpenCommand());
    }

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
     * @since 1.0
     */
    public boolean openDir(File file) throws IOException {
        if (file == null || !file.exists() || !file.isDirectory()) {
            return false;
        }

        String cmd = createOpenDirCommand(file);
        logger.debug("Open dir command created: '{}'", cmd);
        return executeWithRuntime(cmd);
    }

    /**
     * Opens the gui file-explorer window where the specified file is selected.
     *
     * <p>
     * If the file doesn't exist then it will immediately return with {@code false}.
     * <p>
     * Note:
     * <i>On Linux, 'nautilus' should be installed on the system (if it's not by default) <span style="color:red">sudo apt install nautilus</span></i>
     *
     * @param file the file object that represents the file or directory; may be null (then the method will immediately return)
     * @return {@code true} if the window is opened; {@code false} otherwise
     * @throws IOException if some I/O exception occurs
     * @since 1.0
     */
    public boolean openSelect(File file) throws IOException {
        if (file == null || !file.exists()) {
            return false;
        }

        String cmd = createOpenSelectCommand(file);
        logger.debug("Open&select command created: '{}'", cmd);
        return executeWithRuntime(cmd);
    }

    private boolean executeWithRuntime(String command) throws IOException {
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor(1, TimeUnit.SECONDS);
            return true;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
