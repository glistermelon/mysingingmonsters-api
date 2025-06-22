package com.glisterbyte.Localization;

public class CRC32 {

    private static final int[] table = new int[256];

    static {
        for (int i = 0; i < 256; i++) {
            int c = i;
            for (int j = 0; j < 8; j++) {
                if ((c & 1) != 0) {
                    c = (c >>> 1) ^ 0xedb88320;
                } else {
                    c = c >>> 1;
                }
            }
            table[i] = c;
        }
    }

    public static int crc32(int crc, byte[] data) {
        crc = ~crc;
        for (byte b : data) {
            crc = (crc >>> 8) ^ table[(crc ^ b) & 0xFF];
        }
        return ~crc;
    }

}
