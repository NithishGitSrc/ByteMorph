package com.bytemorph.util;

import java.math.BigDecimal;

/**
 * Handles COBOL COMP-3 (packed decimal) data types.
 * In COMP-3 format, each byte contains two decimal digits (except last byte which has sign),
 * packed as nibbles (4-bits each).
 */
public class COMP3 {

    /**
     * Decodes COMP-3 packed decimal data to a Java BigDecimal.
     *
     * @param packedData byte array containing packed decimal data
     * @param scale number of implied decimal places
     * @return BigDecimal containing the decoded value
     * @throws IllegalArgumentException if input data is invalid
     */
    public static BigDecimal decode(byte[] packedData, int scale) {
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
