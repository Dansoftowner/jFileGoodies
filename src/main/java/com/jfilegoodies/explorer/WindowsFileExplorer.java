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

import java.io.File;

import static com.jfilegoodies.util.StringLiterals.QUOTATION;
import static com.jfilegoodies.util.StringLiterals.SPACE;

/**
 * A {@link WindowsFileExplorer} is a {@link FileExplorer} implementation
 * that works on a Microsoft Windows operating system.
 *
 * @author Daniel Gyorffy
 * @since 1.0
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
