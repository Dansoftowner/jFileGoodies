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

package com.jfilegoodies;

import java.io.File;
import java.net.URI;
import java.util.Optional;

import static com.jfilegoodies.util.StringLiterals.DOT;
import static com.jfilegoodies.util.StringLiterals.EMPTY;

/**
 * A FormattedFile is a {@link File} that splits the file-name
 * into a simple-name (that has no extension) and an extension.
 * These can be accessed by the {@link #getSimpleName()}, {@link #getExtension()}
 * methods.
 *
 * @author Daniel Gyorffy
 * @since 1.0
 */
public class FormattedFile extends File {

    private static final String WINDOWS_SEPARATOR = "\\";
    private static final String UNIX_SEPARATOR = "/";

    private final String simpleName;
    private final String extension;

    public FormattedFile(File from) {
        this(from.getPath());
    }

    public FormattedFile(String pathname) {
        super(pathname);
        this.simpleName = nameWithoutExtension();
        this.extension = cropExtension();
    }

    public FormattedFile(String parent, String child) {
        super(parent, child);
        this.simpleName = nameWithoutExtension();
        this.extension = cropExtension();
    }

    public FormattedFile(File parent, String child) {
        super(parent, child);
        this.simpleName = nameWithoutExtension();
        this.extension = cropExtension();
    }

    public FormattedFile(URI uri) {
        super(uri);
        this.simpleName = nameWithoutExtension();
        this.extension = cropExtension();
    }

    private int getLastSeparatorIndex() {
        String fileName = getName();
        return Math.max(
                fileName.lastIndexOf(WINDOWS_SEPARATOR),
                fileName.lastIndexOf(UNIX_SEPARATOR)
        );
    }

    private int getExtensionSeparatorPos() {
        int extensionPos = getName().lastIndexOf(DOT);
        int separatorPos = getLastSeparatorIndex();

        return (separatorPos > extensionPos) ? -1 : extensionPos;
    }

    private String cropExtension() {
        int extensionPos = getExtensionSeparatorPos();
        return extensionPos < 0 ? EMPTY : getName().substring(extensionPos + 1);
    }

    private String nameWithoutExtension() {
        int extensionPos = getExtensionSeparatorPos();
        return extensionPos < 0 ? EMPTY : getName().substring(0, extensionPos);
    }

    /**
     * Returns the file's name without the extension.
     */
    public String getSimpleName() {
        return simpleName;
    }

    /**
     * Returns the extension.
     *
     * @return an empty string ("") if the file hasn't got an extension; otherwise the extension (without the dot (.))
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Returns the extension wrapped in an {@link Optional}
     *
     * @return the extension wrapped in an optional
     */
    public Optional<String> getExtensionOptional() {
        return Optional.ofNullable(hasNoExtension() ? null : getExtension());
    }

    /**
     * @return {@code true} if the file has an extension; {@code false} otherwise
     */
    public boolean hasExtension() {
        return !this.getExtension().replaceAll("\\s+", EMPTY).isEmpty();
    }

    /**
     * @return {@code true} if the file hasn't got an extension; {@code false} otherwise.
     */
    public boolean hasNoExtension() {
        return !hasExtension();
    }

}
