package com.jfilegoodies;

import com.jfilegoodies.util.OsUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Objects;
import java.util.regex.Pattern;

public final class FileGoodies {

    private FileGoodies() {
    }

    private static final String DOT = ".";
    private static final String EMPTY = "";

    public enum FileType {
        FILE, DIRECTORY
    }


    private static String getExtension(String fileName) {
        if (fileName == null) {
            return null;
        }

        int extensionPos = fileName.lastIndexOf(DOT);
        int separatorPos = fileName.lastIndexOf(File.separatorChar);

        return (separatorPos > extensionPos || extensionPos < 0) ? EMPTY : fileName.substring(extensionPos + 1);
    }

    public static boolean isOSExecutable(File file) {
        if (file == null || file.isDirectory())
            return false;

        String regex;
        if (OsUtils.isWindows()) {
            regex = "(exe|bat|bin|cmd|com|cpl|gadget|ins|inx|isu|job|jse|lnk|msc|msi|msp|mst|paf|pif|ps1|reg|rgs|scr|sct|shb|shs|u3p|vb|vbe|vbs|vbscript|ws|wsf|wsh)";
        } else if (OsUtils.isLinux()) {
            regex = "(bin|csh|ksh|out|run|deb)";
        } else if (OsUtils.isMac()) {
            regex = "(action|app|bin|command|csh|osx|workflow)";
        } else {
            regex = "$x^"; //regex that doesn't match anything
        }

        Pattern compiledRegex = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return compiledRegex.matcher(getExtension(file.getName())).matches();
    }

    /**
     * Checks that the path of the file is valid or not.
     * If the file object is null it returns immediately false;
     * <p>
     * Example (on Windows):
     *
     * <pre>
     *     "C:/Users/User/test.txt" -> {@code true}
     *     "C?,:.f" -> {@code false}s
     * </pre>
     *
     * @param file the file that we want to check; may be null
     * @return {@code true} if the filepath is valid; {@code false} otherwise.
     */
    public static boolean hasValidPath(File file) {
        try {
            file.toPath(); //ignored result
            return true;
        } catch (InvalidPathException | NullPointerException e) {
            return false;
        }
    }

    public static boolean hasNotValidPath(File file) {
        return !hasValidPath(file);
    }

    public static String shortenedFilePath(File file, int maxBack) {
        if (Objects.isNull(file) || maxBack < 0) {
            return "";
        }

        StringBuilder shortenedPath = new StringBuilder(file.getName());

        File lastParent = file.getParentFile();
        while (maxBack > 0 && lastParent != null) {
            shortenedPath.insert(0, File.separator).insert(0, lastParent.getName());
            lastParent = lastParent.getParentFile();

            maxBack--;
        }

        if (lastParent != null) {
            shortenedPath.insert(0, "..." + File.separator);
        }

        return shortenedPath.toString();
    }

    public static boolean createFile(File file, FileType fileType) throws IOException {
        if (file == null)
            return false;
        else if (file.exists())
            return true;

        switch (fileType) {
            case FILE:
                return file.createNewFile();
            case DIRECTORY:
                return file.mkdirs();
            default:
                return false; //make the compiler happy
        }
    }

    public static boolean deprecateFile(File file) {
        if (file == null || file.isDirectory())
            return false;

        File directoryOfFile = file.getParentFile();
        String nameOfFile = file.getName();

        File generated;
        do {
            int random = (int) (Math.random() * Math.pow(10, 5));
            generated = new File(directoryOfFile, String.format("%s_%s%d", nameOfFile, "old", random));
        } while (generated.exists());

        return file.renameTo(generated);
    }

}
