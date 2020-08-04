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

import com.jfilegoodies.util.OsUtils;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Provides factory/utility methods for {@link FileExplorer} objects.
 *
 * @author Daniel Gyorffy
 * @since 1.0
 */
public final class FileExplorers {

    /**
     * An LazyExplorer is a {@link FileExplorer} that wraps
     * another {@link FileExplorer} object.
     *
     * <p>
     * The reason why it is useful is that it doesn't throw exception(s) ({@link RuntimeException} or {@link IOException}).
     *
     * <p>
     * To create an instance of this class you can use the {@link FileExplorers#notThrowsException(FileExplorer)}
     * method.
     *
     * <pre>{@code
     * FileExplorer explorer = ...;
     * FileExplorers.ExceptionFreeExplorer exceptionFreeExplorer = FileExplorers.notThrowsException(explorer);
     * //use it
     * }</pre>
     *
     * @since 1.0
     */
    public static final class LazyExplorer extends FileExplorer {
        private final FileExplorer fileExplorer;

        private LazyExplorer(FileExplorer fileExplorer) {
            this.fileExplorer = Objects.requireNonNull(fileExplorer, "The fileExplorer object mustn't be null");
        }

        @Override
        protected String createOpenCommand() {
            return null;
        }

        @Override
        protected String createOpenDirCommand(File file) {
            return null;
        }

        @Override
        protected String createOpenSelectCommand(File file) {
            return null;
        }

        @Override
        public boolean open() {
            try {
                return this.fileExplorer.open();
            } catch (IOException | RuntimeException e) {
                return false;
            }
        }

        @Override
        public boolean openDir(File file) {
            try {
                return this.fileExplorer.openDir(file);
            } catch (IOException | RuntimeException e) {
                return false;
            }
        }

        @Override
        public boolean openSelect(File file) {
            try {
                return this.fileExplorer.openSelect(file);
            } catch (IOException | RuntimeException e) {
                return false;
            }
        }
    }

    private static final class NullFileExplorer extends FileExplorer {
        @Override
        protected String createOpenCommand() {
            return null;
        }

        @Override
        protected String createOpenDirCommand(File file) {
            return null;
        }

        @Override
        protected String createOpenSelectCommand(File file) {
            return null;
        }

        @Override
        public boolean open() {
            return false;
        }

        @Override
        public boolean openDir(File file) {
            return false;
        }

        @Override
        public boolean openSelect(File file) {
            return false;
        }
    }

    /**
     * Creates an instance of the right {@link FileExplorer} implementation that is compatible
     * with the current OS.
     *
     * @return the {@link FileExplorer} implementation.
     * @since 1.0
     */
    public static FileExplorer get() {
        if (OsUtils.isWindows()) {
            return new WindowsFileExplorer();
        } else if (OsUtils.isLinux()) {
            return new LinuxFileExplorer();
        } else if (OsUtils.isMac()) {
            return new MacFileExplorer();
        } else {
            return new NullFileExplorer();
        }
    }

    /**
     * Wraps a {@link FileExplorer} into a {@link LazyExplorer} that doesn't throw exceptions.
     *
     * @param explorer the original {@link FileExplorer} object; shouldn't be null
     * @return the {@link LazyExplorer} object.
     * @throws NullPointerException if the explorer is null
     * @see LazyExplorer
     * @since 1.0
     */
    public static LazyExplorer notThrowsException(FileExplorer explorer) {
        return new LazyExplorer(explorer);
    }
}
