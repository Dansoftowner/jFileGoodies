package com.jfilegoodies;

import java.io.File;
import java.net.URI;

import static com.jfilegoodies.util.StringLiterals.DOT;
import static com.jfilegoodies.util.StringLiterals.EMPTY;

/**
 * A FormattedFile is a {@link File} that splits the file-name
 * into a simple-name (that has no extension) and an extension.
 * These can be accessed by the {@link #getSimpleName()}, {@link #getExtension()}
 * methods.
 *
 * @author Daniel Gyorffy
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
