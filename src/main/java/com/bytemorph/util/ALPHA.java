package com.bytemorph.util;

import lombok.extern.slf4j.Slf4j;
import java.io.UnsupportedEncodingException;

/**
 * Utility class for converting EBCDIC (CP1047) encoded alphabetic data to ASCII.
 * This class handles the conversion of mainframe EBCDIC character data (PIC X) to Java Strings.
 */
@Slf4j
public class ALPHA {
    
    /**
     * Converts EBCDIC encoded bytes to a Java String using CP1047 character encoding.
     *
     * @param bytes EBCDIC encoded byte array (must not be null or empty)
     * @return String containing the decoded ASCII characters
     * @throws IllegalArgumentException if the input byte array is null or empty
     * @throws RuntimeException if EBCDIC decoding fails
     */
    public static String decode(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            throw new IllegalArgumentException("Input bytes cannot be null or empty");
        }
        try {
            String data = new String(bytes, "CP1047");
            log.debug("Decoded ALPHA data: {}", data);
            return data;
        } catch (UnsupportedEncodingException e) {
            log.error("Failed to decode ALPHA data", e);
            throw new RuntimeException("EBCDIC decode error", e);
        }
    }
}
