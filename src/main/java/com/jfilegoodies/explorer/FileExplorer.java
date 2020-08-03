package com.jfilegoodies.explorer;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public abstract class FileExplorer {
    public void show(File file) throws IOException {
        Objects.requireNonNull(file, "The file object mustn't be null");
    }
}
