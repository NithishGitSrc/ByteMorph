package com.bytemorph.util;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Handles COBOL COMP (USAGE COMPUTATIONAL) binary data types.
 * Supports 2-byte (PIC S9(4)), 4-byte (PIC S9(8)), and 8-byte (PIC S9(18)) formats.
 */
@Slf4j
public class COMP {

    /**
     * Decodes COMP binary data to a Java long value.
     *
     * @param data byte array containing COMP data (must be 2, 4, or 8 bytes)
     * @return decoded long value
     * @throws IllegalArgumentException if data length is invalid or data is null/empty
     */
    public static long decode(byte[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Input bytes cannot be null or empty");
        }
        ByteBuffer bb = ByteBuffer.wrap(data);
        bb.order(ByteOrder.BIG_ENDIAN);

        return switch (data.length) {
            case 2 -> bb.getShort();
            case 4 -> bb.getInt();
            case 8 -> bb.getLong();
            default -> throw new IllegalArgumentException("Unsupported COMP field size: " + data.length);
        };

    }


    /**
     * Decodes COMP binary data with implied decimal places.
     *
     * @param data byte array containing COMP data
     * @param decimalPlaces number of implied decimal places
     * @return decoded value as double with applied decimal scaling
     * @throws IllegalArgumentException if decimal places is negative or data length is invalid
     */
    public static double decode(byte[] data, int decimalPlaces) {
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException("Decimal places cannot be negative");
        }
        ByteBuffer bb = ByteBuffer.wrap(data);
        bb.order(ByteOrder.BIG_ENDIAN);

        long rawValue;
        switch (data.length) {
            case 2:
                rawValue = bb.getShort(); // 16-bit signed
                break;
            case 4:
                rawValue = bb.getInt();   // 32-bit signed
                break;
            case 8:
                rawValue = bb.getLong();  // 64-bit signed
                break;
            default:
                throw new IllegalArgumentException("Unsupported COMP field size: " + data.length);
        }

        if (decimalPlaces > 0) {
            return rawValue / Math.pow(10, decimalPlaces);
        } else {
            return rawValue;
        }
    }


}
