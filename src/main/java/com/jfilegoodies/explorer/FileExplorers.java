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
        public boolean open() {
            try {
                return this.fileExplorer.open();
            } catch (IOException | RuntimeException ignored) {
                return false;
            }
        }

        @Override
        public boolean openDir(File file) {
            try {
                return this.fileExplorer.openDir(file);
            } catch (IOException | RuntimeException ignored) {
                return false;
            }
        }

        @Override
        public boolean openSelect(File file) {
            try {
                return this.fileExplorer.openSelect(file);
            } catch (IOException | RuntimeException ignored) {
                return false;
            }
        }
    }

    private static class NullFileExplorer extends FileExplorer {
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
