package com.chimpim.rfidmodule.internal;

public final class Utils {

    public static String getJnaLibraryName() throws RuntimeException {
        final String os = System.getProperty("os.name").toLowerCase();
        final String arch = System.getProperty("os.arch");
        for (JnaSupport it : JnaSupport.values()) {
            if (os.contains(it.os) && arch.contains(it.arch)) {
                return it.libraryName;
            }
        }
        throw new RuntimeException("此操作系统的Jna方式读卡器");
    }

    public static String getJniLibraryName() throws RuntimeException {
        final String os = System.getProperty("os.name").toLowerCase();
        final String arch = System.getProperty("os.arch");
        for (JniSupport it : JniSupport.values()) {
            if (os.contains(it.os) && arch.contains(it.arch)) {
                return it.libraryName;
            }
        }
        throw new RuntimeException("此操作系统的Jna方式读卡器");
    }

    public static boolean isSupportJnaReader() {
        try {
            getJnaLibraryName();
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public static boolean isSupportJniReader() {
        try {
            getJniLibraryName();
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }


}
