package com.util.ming.projectutil.utils;

import java.nio.ByteBuffer;
import java.util.UUID;

public class CinHelper {
    private CinHelper() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static final String EmptyString = "";
    public static final String COMMA = ",";
    public static byte[] token;

    public static byte[] getUUID() {
        UUID uuid = UUID.randomUUID();
        ByteBuffer bf = ByteBuffer.allocate(16);
        bf.putLong(uuid.getLeastSignificantBits());
        bf.putLong(uuid.getMostSignificantBits());
        bf.flip();
        return bf.array();
    }

    public static String getHexUUID() {
        return bytes2Hex(getUUID());
    }

    public static byte[] long2Bytes(long value) {
        if (value != 0) {
            int zeros = Long.numberOfLeadingZeros(value);
            int length = 8 - zeros / 8;
            byte[] rawValue = new byte[length];
            for (int i = 0; i < length; i++) {
                rawValue[i] = (byte) (value >>> ((i) * 8));
            }
            return rawValue;
        } else {
            return new byte[]{(byte) 0};
        }
    }

    /**
     * 字节数组表示转成16进制的字符串
     *
     * @param value 转换的字节数组
     * @return 16进制格式的字符串
     **/
    public static String bytes2Hex(byte[] value) {
        StringBuilder sb = new StringBuilder();
        for (byte b : value) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }


    /**
     * 16进制的字符串表示转成字节数组
     *
     * @param hexString 16进制格式的字符串
     * @return 转换后的字节数组
     **/
    public static byte[] toByteArray(String hexString) {
        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {
            // 因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }
}
