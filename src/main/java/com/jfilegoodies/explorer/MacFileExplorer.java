package com.jfilegoodies.explorer;

import java.io.File;

public class MacFileExplorer extends FileExplorer {
    @Override
    public void show(File file) {
        throw new UnsupportedOperationException("Opening files on Linux is not supported (yet)");
    }
}
