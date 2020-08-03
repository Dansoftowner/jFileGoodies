package com.jfilegoodies.explorer;

import com.jfilegoodies.util.OsUtils;

import java.io.File;
import java.io.IOException;

public class FileExplorerDemo {

    public static void main(String[] args) {
        FileExplorerDemo demoInstance = new FileExplorerDemo();
        try {
            demoInstance.basicOpen(new File("build.gradle"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        demoInstance.explicitOpen(new File(".gitignore"));
        demoInstance.notThrowsExceptionOpen(new File("gradlew.bat"));
    }

    public void basicOpen(File file) throws IOException {
        FileExplorer fileExplorer = FileExplorers.get();
        fileExplorer.show(file);
    }

    public void notThrowsExceptionOpen(File file) {
        FileExplorers.ExceptionFreeExplorer fileExplorer = FileExplorers.notThrowsException(FileExplorers.get());
        fileExplorer.show(file);
    }

    public void explicitOpen(File file) {
        FileExplorer explorer =
                OsUtils.isWindows() ? new WindowsFileExplorer() :
                        OsUtils.isLinux() ? new LinuxFileExplorer() :
                                OsUtils.isMac() ? new MacFileExplorer() : null;

        if (explorer != null) {
            FileExplorers.notThrowsException(explorer).show(file);
        }
    }

}
