package com.bytemorph.testdata;

public class TestData {

    /*
    Test data for Numeric Field
    */
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

    /*
    * Test Data for COMP
    * */

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
    public static final byte[] positive4321 = new byte[]{ (byte) 0x10, (byte) 0xE1 };// 4321
    public static final byte[] neg_s9_4_v2_comp = new byte[]{ (byte) 0xEF, (byte) 0x1F }; //-4321

    /* Test Data for COMP-3*/

    public static final byte[] positive1234 = new byte[] { 0x01, 0x23, 0x4C };  // 1234
    // Example 1: 9(5) COMP-3 → 12345

    public static final byte[] unsignedInt = new byte[]{0x12, 0x34, 0x5C}; // sign nibble C = positive
    // Example 2: 9(3)V9(2) COMP-3 → 123.45

    public static final byte[] impliedDecimal = new byte[]{0x12, 0x34, 0x5C};
    // Example 3: S9(4)V9(1) COMP-3 → -1234.5

    public static final byte[] signedDecimal = new byte[]{0x12, 0x34, 0x5D}; // sign nibble D = negative
    // S9(5) COMP-3 → +12345

    public static final byte[] signedPositive = new byte[]{0x12, 0x34, 0x5C};
    // S9(5) COMP-3 → -12345

    public static final byte[] signedNegative = new byte[]{0x12, 0x34, 0x5D};
    // Example 4: 9(6) COMP-3 → 123456

    public static final byte[] unsigned6 = new byte[]{0x12, 0x34, 0x56, 0x0C};
}
