package org.example.bytemorph.testdata;

public class TestData {


    public static final byte[] pos_9_4 = new byte[] {
            (byte) 0xF1, // 1
            (byte) 0xF2, // 2
            (byte) 0xF3, // 3
            (byte) 0xF4  // 4 (+)
    }; // ➜ 1234

    public static final byte[] neg_9_6 = new byte[] {
            (byte) 0xF9, // 9
            (byte) 0xF8, // 8
            (byte) 0xF7, // 7
            (byte) 0xF6, // 6
            (byte) 0xF5, // 5
            (byte) 0xD4  // 4 (-)
    }; // ➜ -987654

    public static final byte[] pos_3v2 = new byte[] {
            (byte) 0xF1, // 1
            (byte) 0xF2, // 2
            (byte) 0xF3, // 3
            (byte) 0xF4, // 4
            (byte) 0xF5  // 5 (+)
    }; // ➜ 123.45 (decimalPlaces = 2)

    public static final byte[] neg_4v2 = new byte[] {
            (byte) 0xF0, // 0
            (byte) 0xF1, // 1
            (byte) 0xF2, // 2
            (byte) 0xF3, // 3
            (byte) 0xF4, // 4
            (byte) 0xD5  // 5 (-)
    }; // ➜ -0123.45 (decimalPlaces = 2)


    // Max for 2-byte COMP: 32767
    public static final byte[] pos_9_5_comp = new byte[] { 0x7F, (byte) 0xFF };

    // Max for 4-byte COMP: 2,147,483,647
    public static final byte[] pos_4_byte_comp = new byte[] { 0x7F, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF };

    // Max for 8-byte COMP: 9,223,372,036,854,775,807L
    public static final byte[] pos_8_byte_comp = new byte[] {
            0x7F, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
            (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF
    };


    public static final byte[] pos_s9_5_comp = {(byte)0x00, (byte)0x7B}; // 123
    public static final byte[] neg_s9_5_comp = new byte[] {(byte)0xFE, (byte)0x38}; // -456
    public static final byte[] positive4321 = new byte[]{ (byte) 0x10, (byte) 0xE1 };
    public static final byte[] neg_s9_4_v2_comp = new byte[]{ (byte) 0xEF, (byte) 0x1F };

}
