/*
 * Copyright 2020 DansoftOwner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jfilegoodies;

import com.jfilegoodies.util.OsUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import static com.jfilegoodies.util.StringLiterals.*;

/**
 * Provides some special utilities for files.
 *
 * @author Daniel Gyorffy
 * @since 1.0
 */
public final class FileGoodies {

    private FileGoodies() {
    }

    /**
     * Represents the two type of a file.
     */
    public enum FileType {
        FILE, DIRECTORY
    }

    /**
     * Lists all executable file-extensions on the current OS.
     *
     * <a href='https://www.lifewire.com/list-of-executable-file-extensions-2626061'>List of Executable File Extensions</a>
     *
     * @return the List of extensions
     * @since 1.0
     */
    public static List<String> getExecutableExtensions() {
        return OsUtils.isWindows() ? Arrays.asList(
                "exe", "bat", "bin", "cmd", "com", "cpl", "gadget", "ins",
                "inx", "isu", "job", "jse", "lnk", "msc", "msi", "msp",
                "mst", "paf", "pif", "ps1", "reg", "rgs", "scr", "sct",
                "shb", "shs", "u3p", "vb", "vbe", "vbs", "vbscript", "ws",
                "wsf", "wsh"
        ) : OsUtils.isLinux() ? Arrays.asList(
                "bin", "csh",
                "ksh", "out",
                "run", "deb",
                "sh", "rpm"
        ) : OsUtils.isMac() ? Arrays.asList(
                "action", "app",
                "bin", "command",
                "csh", "osx",
                "workflow"
        ) : Collections.emptyList();
    }

    /**
     * Checks that the file is an executable file on the current OS.
     *
     * <p>
     * If the file is a directory or it hasn't got an extension the method will immediately return.
     *
     * <p>
     * Example (on Windows) :
     * <pre>
     *     isOSExecutable(new File("file.txt")) => false
     *     isOSExecutable(new File("program.exe") => true
     *     isOSExecutable(new File("script.bat") => true
     * </pre>
     *
     * <i>Note: it checks the file's executability only by it's extension, so it's may be not enough for you</i>
     *
     * <a href='https://www.lifewire.com/list-of-executable-file-extensions-2626061'>List of Executable File Extensions</a>
     *
     * @param file the file to check
     * @return {@code true} if the file is an executable file; {@code false} otherwise
     * @since 1.0
     */
    public static boolean isOSExecutable(File file) {
        FormattedFile formattedFile;
        if (file == null || file.isDirectory() || (formattedFile = new FormattedFile(file)).hasNoExtension())
            return false;

        //creating the regex that will match the file's extension if that's an executable type
        String regex;
        if (OsUtils.isWindows()) {
            regex = "(exe|bat|bin|cmd|com|cpl|gadget|ins|inx|isu|job|jse|lnk|msc|msi|msp|mst" +
                    "|paf|pif|ps1|reg|rgs|scr|sct|shb|shs|u3p|vb|vbe|vbs|vbscript|ws|wsf|wsh)";
        } else if (OsUtils.isLinux()) {
            regex = "(bin|csh|ksh|out|run|deb|sh|rpm)";
        } else if (OsUtils.isMac()) {
            regex = "(action|app|bin|command|csh|osx|workflow)";
        } else {
            regex = "$x^"; //regex that doesn't match anything
        }

        Pattern compiledRegex = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return compiledRegex.matcher(formattedFile.getExtension()).matches();
    }

    /**
     * Returns the negated value of {@link #isOSExecutable(File)}.
     *
     * @see #isOSExecutable(File)
     * @since 1.0
     */
    public static boolean isNotOSExecutable(File file) {
        return !isOSExecutable(file);
    }

    /**
     * Checks that the path of the file is valid or not.
     * If the file object is null it returns immediately false;
     * <p>
     * Example (on Windows):
     *
     * <pre>
     *     "C:/Users/User/test.txt" -> {@code true}
     *     "C?,:.f" -> {@code false}
     * </pre>
     *
     * @param file the file that we want to check; may be null
     * @return {@code true} if the filepath is valid; {@code false} otherwise.
     * @since 1.0
     */
    public static boolean hasValidPath(File file) {
        try {
            file.toPath(); //ignored result
            return true;
        } catch (InvalidPathException | NullPointerException e) {
            return false;
        }
    }

    /**
     * Returns the negated value of {@link #hasValidPath(File)}.
     *
     * @see #hasValidPath(File)
     * @since 1.0
     */
    public static boolean hasNotValidPath(File file) {
        return !hasValidPath(file);
    }


    /**
     * Creates a shortened path from a file.
     *
     * <p>
     * It is useful when you don't want to use a very long file-path
     * and you want to short it.
     *
     * <p><br>
     * Let's say you have a file-path <b>/images/background/wallpaper/design/First.png</b>.
     * You want to create a path that only shows the 2 previous directory: <b>.../wallpaper/design/First.png</b>.
     * <p>
     * You can specify the:
     * <ul>
     *     <li><b>prefix</b> <i>it was '...' in the previous example</i></li>
     *     <li><b>separator</b> <i>it was '/' in the previous example</i></li>
     *     <li><b>maxBack</b> - it specifies how many parent directories you want to show.
     *         <i>it was 2 in the previous example</i></li>
     * </ul>
     * <p>
     * <b>More examples</b>
     * <pre>
     *  shortenedFilePath(new File("programFiles/thePrg/appdata/inf/config.prop"), "<DEFAULT>", '\\', 0) => "<DEFAULT>\config.prop"
     *  shortenedFilePath(new File("users/user0/documents/Plans.docx"), "[USER_HOME]", '/', 1) => "[USER_HOME]/documents/Plans.docx"
     * </pre>
     *
     * @param file      the file that we want to show a shorter path of
     * @param prefix    the prefix that will be at the start of the string;
     *                  basically that hides the directories that are not displayed
     * @param separator the separator character that will separate the paths.
     * @param maxBack   specifies how many directories we want to show before the file's name;
     *                  if it's less than 0 the method will return an empty string.
     * @return the shortened path
     * @since 1.0
     */
    public static String shortenedFilePath(File file, String prefix, char separator, int maxBack) {
        if (file == null || maxBack < 0) {
            return EMPTY;
        }

        StringBuilder shortenedPath = new StringBuilder(file.getName());

        File lastParent = file.getParentFile();
        while (maxBack > 0 && lastParent != null) {
            shortenedPath.insert(0, separator).insert(0, lastParent.getName());
            lastParent = lastParent.getParentFile();

            maxBack--;
        }

        if (lastParent != null) {
            shortenedPath
                    .insert(0, separator)
                    .insert(0, prefix);
        }

        return shortenedPath.toString();
    }

    /**
     * Calls the {@link #shortenedFilePath(File, String, char, int)} method with the prefix
     * <b>"..."<b>.
     *
     * @see #shortenedFilePath(File, String, char, int)
     * @since 1.0
     */
    public static String shortenedFilePath(File file, int maxBack, char separator) {
        return shortenedFilePath(file, "...", separator, maxBack);
    }

    /**
     * Calls the {@link #shortenedFilePath(File, String, char, int)} with the prefix
     * <b>"..."</b> and with the default separator <b>{@link File#separatorChar}</b>.
     *
     * @see #shortenedFilePath(File, String, char, int)
     * @since 1.0
     */
    public static String shortenedFilePath(File file, int maxBack) {
        return shortenedFilePath(file, maxBack, File.separatorChar);
    }

    /**
     * This method creates a {@link File} object that is a modified version of
     * the given file. It's produced this way:
     * <ul>
     *     <li>Generates a random 5-digit number</li>
     *     <li>Adds the '_old' word to the original name of the file</li>
     *     <li>Adds the random number to the end of the file name</li>
     * </ul>
     * The file that this new {@link File} object represents is definitely
     * not existing (because the process that is described above will be repeated
     * while we get a file that is not existing).
     * <pre>
     *     deprecateFile(File("file.txt")) => File("file_old3102.txt")
     *     deprecateFile(File("path/to/someVideo.mp4")) => File("path/to/someVideo_old4916")
     * </pre>
     *
     * <p>
     * It is good if let's say you want to save a new file but with that name a file already exist
     * and you don't want to delete the existing file, you just want to rename it.
     * <pre>{@code
     *         //the file that we want to save to
     *         File toSave = ...;
     *         if (toSave.exists()) {
     *             //if a file already with this name exist we rename it
     *             File deprecateFile = FileGoodies.deprecateFile(toSave);
     *             toSave.renameTo(deprecateFile);
     *         }
     *
     *         //now we can easily save to the file
     * }</pre>
     *
     * @param file the file that we want to rename
     * @since 1.0
     */
    public static File deprecateFile(File file) {
        if (file == null || file.isDirectory())
            return null;

        File directoryOfFile = file.getParentFile();
        FormattedFile formattedFile = new FormattedFile(file);
        String simpleName = formattedFile.getSimpleName();
        String extension = formattedFile.getExtension();

        File generated;
        do {
            int random = (int) (Math.random() * Math.pow(10, 5));
            generated = new File(directoryOfFile, String.format("%s_%s%d.%s", simpleName, "old", random, extension));
        } while (generated.exists());

        return generated;
    }

    @Deprecated
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


}
