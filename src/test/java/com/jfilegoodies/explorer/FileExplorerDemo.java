package com.jfilegoodies.explorer;

import java.io.File;

public class FileExplorerDemo {

    public static void main(String[] args) {
        //getting the platform-specific fileExplorer
        FileExplorer fileExplorer = FileExplorers.get();
        //wrapping the explorer to another explorer-object that doesn't throw exception(s) when we call the methods
        FileExplorers.LazyExplorer lazyExplorer = FileExplorers.notThrowsException(fileExplorer);

        lazyExplorer.open(); //opens the gui-explorer
        lazyExplorer.openDir(new File(".idea")); //opens the directory
        lazyExplorer.openDir(new File(".gitignore")); /* it doesn't do anything and returns false because
                                                                           the file is not a directory */
        lazyExplorer.openSelect(new File("src")); //opens the explorer and selects the file
        lazyExplorer.openSelect(new File("build.gradle"));
    }

}
