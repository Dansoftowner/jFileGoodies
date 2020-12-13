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
        return NAUTILUS;
    }

    @Override
    protected String createOpenDirCommand(File file) {
        return new StringBuilder()
                .append(XDG_OPEN)
                .append(SPACE)
                .append(file.getPath())
                .toString();
    }

    @Override
    protected String createOpenSelectCommand(File file) {
        return new StringBuilder()
                .append(NAUTILUS)
                .append(SPACE)
                .append(file.getPath())
                .toString();
    }
}
