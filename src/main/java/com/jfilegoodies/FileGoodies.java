package com.jfilegoodies;

import com.jfilegoodies.util.OsUtils;

import java.io.File;
import java.util.regex.Pattern;

public final class FileGoodies {

    private static final String DOT = ".";
    private static final String EMPTY = "";

    private FileGoodies() {
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
}
