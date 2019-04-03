package com.chimpim.rfidmodule.internal;

public final class HexStringUtils {


    public static String bytesToHexString(byte[] bytes) {
        return bytesToHexString(bytes, 0, bytes.length);
    }

    public static String bytesToHexString(byte[] bytes, int offset, int size) {
        if (null == bytes) return "";
        StringBuilder strBuilder = new StringBuilder();
        for (int i = offset; i < size; i++) {
            byte b = bytes[i];
            String hex = Integer.toHexString(0xFF & b);
            if (1 == hex.length()) {
                strBuilder.append("0");
            }
            strBuilder.append(hex.toUpperCase());
        }
        return strBuilder.toString();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null) return new byte[0];
        int len = (hexString.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hexString.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (charToByte(achar[pos]) << 4 | charToByte(achar[pos + 1]));
        }
        return result;
    }

    // 字符转字节
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}