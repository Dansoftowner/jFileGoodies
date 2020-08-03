package com.jfilegoodies.explorer;

import com.jfilegoodies.util.OsUtils;

import java.io.File;
import java.io.IOException;

public final class FileExplorers {


    public static class ExceptionFreeExplorer extends FileExplorer {
        private final FileExplorer fileExplorer;

        private ExceptionFreeExplorer(FileExplorer fileExplorer) {
            this.fileExplorer = fileExplorer;
        }

        @Override
        public void show(File file) {
            try {
                this.fileExplorer.show(file);
            } catch (IOException | RuntimeException ignored) {
            }
        }
    }

    private static class NullFileExplorer extends FileExplorer {
        @Override
        public void show(File file) throws IOException {
            //empty
        }
    }


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

    public static ExceptionFreeExplorer notThrowsException(FileExplorer toDecorate) {
        return new ExceptionFreeExplorer(toDecorate);
    }
}
