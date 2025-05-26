package org.example.bytemorph.utility;

import java.math.BigDecimal;

public class COMP3 {

    public static void main(String[] args) {
        System.out.println("==== COMP-3 Test ====");

        byte[] positive1234 = new byte[] { 0x01, 0x23, 0x4C };  // 1234
        System.out.println("Mocked +1234: " + decodeComp3(positive1234, 0));
//        System.out.println("Mocked -1234: " + decodeComp3(negative1234, 0));

        // Example 1: 9(5) COMP-3 → 12345
        byte[] unsignedInt = new byte[]{0x12, 0x34, 0x5C}; // sign nibble C = positive
        System.out.println("9(5) COMP-3: " + decodeComp3(unsignedInt, 0));

        // Example 2: 9(3)V9(2) COMP-3 → 123.45
        byte[] impliedDecimal = new byte[]{0x12, 0x34, 0x5C};
        System.out.println("9(3)V9(2) COMP-3: " + decodeComp3(impliedDecimal, 2)); // 2 decimal places

        // Example 3: S9(4)V9(1) COMP-3 → -1234.5
        byte[] signedDecimal = new byte[]{0x12, 0x34, 0x5D}; // sign nibble D = negative
        System.out.println("S9(4)V9(1) COMP-3: " + decodeComp3(signedDecimal, 1)); // 1 decimal place

        // S9(5) COMP-3 → +12345
        byte[] signedPositive = new byte[]{0x12, 0x34, 0x5C};
        System.out.println("S9(5) COMP-3 (+): " + decodeComp3(signedPositive, 0));

        // S9(5) COMP-3 → -12345
        byte[] signedNegative = new byte[]{0x12, 0x34, 0x5D};
        System.out.println("S9(5) COMP-3 (-): " + decodeComp3(signedNegative, 0));


        // Example 4: 9(6) COMP-3 → 123456
        byte[] unsigned6 = new byte[]{0x12, 0x34, 0x56, 0x0C};
        System.out.println("9(6) COMP-3: " + decodeComp3(unsigned6, 0));
    }

    /**
     * Decode COMP-3 packed decimal to Java BigDecimal
     *
     * @param packedData Byte array from mainframe
     * @param scale      Number of implied decimal digits (e.g., 2 for 9(3)V9(2))
     * @return BigDecimal decoded value
     */
    public static BigDecimal decodeComp3(byte[] packedData, int scale) {
        StringBuilder digits = new StringBuilder();

        // Process all but last byte
        for (int i = 0; i < packedData.length - 1; i++) {
            int b = packedData[i] & 0xFF;
            digits.append((b >> 4) & 0x0F); // high nibble
            digits.append(b & 0x0F);        // low nibble
        }

        // Last byte: one digit + sign nibble
        int lastByte = packedData[packedData.length - 1] & 0xFF;
        digits.append((lastByte >> 4) & 0x0F); // digit
        int signNibble = lastByte & 0x0F;

        // Determine sign
        boolean isNegative = (signNibble == 0x0D || signNibble == 0x0B); // D = negative, B = alternate negative

        BigDecimal value = new BigDecimal(digits.toString());
        if (scale > 0) value = value.movePointLeft(scale);
        if (isNegative) value = value.negate();

        return value;
    }
}
