package com.chimpim.rfidmodule;

import com.chimpim.rfidmodule.internal.Utils;

public final class RfidModule {

    private RfidModule() {
        throw new RuntimeException("此类不允许实例化");
    }

    public static Reader newJnaReader() {
        if (isSupportJna()) {
            return new JnaReader();
        } else {
            throw new RuntimeException("此操作系统的Jna方式读卡器");
        }
    }

    public static Reader newJniReader() {
        if (isSupportJni()) {
            return new JniReader();
        } else {
            throw new RuntimeException("此操作系统的Jni方式读卡器");
        }
    }

    public static boolean isSupportJna() {
        return Utils.isSupportJnaReader();
    }

    public static boolean isSupportJni() {
        return Utils.isSupportJniReader();
    }

}
