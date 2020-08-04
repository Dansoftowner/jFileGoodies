package com.jfilegoodies.explorer;

import java.io.File;
import java.io.IOException;

public class LinuxFileExplorer extends FileExplorer {
    @Override
    public boolean open() throws IOException {
        throw new UnsupportedOperationException("Opening files on Linux is not supported (yet)");
    }

    @Override
    public boolean openDir(File file) throws IOException {
        throw new UnsupportedOperationException("Opening files on Linux is not supported (yet)");
    }

    @Override
    public boolean openSelect(File file) {
        throw new UnsupportedOperationException("Opening files on Linux is not supported (yet)");
    }
}
