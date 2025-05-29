package com.bytemorph.util;

import java.math.BigDecimal;

/**
 * Handles COBOL zoned decimal (DISPLAY) numeric fields.
 * In zoned decimal format, each byte represents one digit with a zone portion.
 */
public class NumField {

    /**
     * Decodes zoned decimal data to a String representation.
     *
     * @param data byte array containing zoned decimal data
     * @param decimalPlaces number of implied decimal places
     * @param signed whether to include sign in output
     * @return String representation of the numeric value
     * @throws IllegalArgumentException if sign zone nibble is invalid
     */
    public static String decode(byte[] data, int decimalPlaces, boolean signed) {
        StringBuilder digits = new StringBuilder();
        boolean isNegative = false;

        for (int i = 0; i < data.length; i++) {
            int b = data[i] & 0xFF;

            // Extract digit (low nibble)
            int digit = b & 0x0F;

            // Check for sign in last byte
            if (i == data.length - 1) {
                int zone = (b >> 4) & 0x0F;
                if (zone == 0xD) {
                    isNegative = true;
                } else if (zone != 0xF && zone != 0xC) {
                    throw new IllegalArgumentException("Invalid sign zone nibble: " + Integer.toHexString(zone));
                }
            }

            digits.append(digit);
        }

        if (decimalPlaces > 0) {
            digits.insert(digits.length() - decimalPlaces, '.');
        }

//            BigDecimal result = new BigDecimal(digits.toString());
//            return isNegative ? result.negate() : result;
        if(!isNegative && signed) digits = new StringBuilder("+"+digits.toString());
        return isNegative ? "-"+digits.toString() : digits.toString();
    }

    /**
     * Converts a BigDecimal to zoned decimal byte array.
     *
     * @param value the BigDecimal to convert
     * @param length total length in bytes
     * @param decimalPlaces number of decimal places
     * @return byte array containing zoned decimal representation
     */
    public static byte[] toZonedDecimal(BigDecimal value, int length, int decimalPlaces) {
        String digits = value.movePointRight(decimalPlaces).abs().toPlainString();
        while (digits.length() < length) {
            digits = "0" + digits;
        }

        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            int d = digits.charAt(i) - '0';
            int zone = (i == length - 1)
                    ? (value.signum() < 0 ? 0xD : 0xF)
                    : 0xF;
            result[i] = (byte) ((zone << 4) | d);
        }

        return result;
    }


}

