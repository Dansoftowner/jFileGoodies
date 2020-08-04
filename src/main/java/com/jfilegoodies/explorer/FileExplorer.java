package com.jfilegoodies.explorer;

import java.io.File;
import java.io.IOException;

public abstract class FileExplorer {

    public abstract boolean open() throws IOException;

    public abstract boolean openDir(File file) throws IOException;

    public abstract boolean openSelect(File file) throws IOException;
}
