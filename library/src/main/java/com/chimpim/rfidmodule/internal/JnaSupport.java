package com.chimpim.rfidmodule.internal;

public enum JnaSupport {

    LINUX_EB_ARM("ModuleAPI_Eb_arm", "linux", "arm"),
    LINUX_X86_32B("ModuleAPI_x86_32b", "linux", "86"),
    LINUX_X86_64B("ModuleAPI_x86_64b", "linux", "64"),
    WINDOWS_X86_32B("ModuleAPI_x86_32b", "windows", "86");

    public final String libraryName;
    public final String os;
    public final String arch;

    JnaSupport(String libraryName, String os, String arch) {
        this.libraryName = libraryName;
        this.os = os;
        this.arch = arch;
    }

}
