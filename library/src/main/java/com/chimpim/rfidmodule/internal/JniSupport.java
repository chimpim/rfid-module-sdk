package com.chimpim.rfidmodule.internal;

public enum JniSupport {

    LINUX_X86_32B("ModuleAPIJni_32b", "linux", "86"),
    LINUX_X86_64B("ModuleAPIJni_64b", "linux", "64"),
    WINDOWS_X86_32B("ModuleAPIJni_32b", "windows", "86"),
    WINDOWS_X86_64B("ModuleAPIJni_64b", "windows", "64");

    public final String libraryName;
    public final String os;
    public final String arch;

    JniSupport(String libraryName, String os, String arch) {
        this.libraryName = libraryName;
        this.os = os;
        this.arch = arch;
    }
}
