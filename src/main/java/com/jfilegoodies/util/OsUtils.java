package com.jfilegoodies.util;

public final class OsUtils {

    private static final String os = System.getProperty("os.name");

    private static final boolean WINDOWS = os.startsWith("Windows");
    private static final boolean MAC = os.startsWith("Mac");
    private static final boolean LINUX = os.startsWith("Linux") ;
    private static final boolean SOLARIS = os.startsWith("SunOS");

    public static boolean isWindows() {
        return WINDOWS;
    }

    public static boolean isMac() {
        return MAC;
    }

    public static boolean isLinux() {
        return LINUX;
    }

    public static boolean isSolaris() {
        return SOLARIS;
    }

    private OsUtils() {
    }
}
