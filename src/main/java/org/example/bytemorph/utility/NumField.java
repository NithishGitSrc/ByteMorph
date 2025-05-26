package org.example.bytemorph.utility;

import java.math.BigDecimal;

public class NumField {

    public static void test() {
        System.out.println("----------#TESTING Num Fields#----------");
        main(new String[]{});

        System.out.println("~~~~~~~~~~~~~~xxxxxxxxxxx~~~~~~~~~~~~~~~~~");
    }

    public static String decode(byte[] bytes, int decimalPlaces, boolean signed){

        String data = null;
        data = parseZonedDecimal(bytes,decimalPlaces,signed).toString();
        System.out.println(data);
        return data;

    }



        public static String parseZonedDecimal(byte[] data, int decimalPlaces, boolean signed) {
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

        // Optional: Convert integer to zoned decimal bytes
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

        public static void print(byte[] byteArr, int dp, boolean signed){
            String result = parseZonedDecimal(byteArr, dp,signed);
            System.out.println(result);  // 123.45
        }

        // Test
        public static void main(String[] args) {
            // Example: -012
            byte[] neg = new byte[]{ (byte) 0xF0, (byte) 0xF1, (byte) 0xD2 }; // -012
            String parsed = parseZonedDecimal(neg, 0,true);
            System.out.println("Parsed: " + parsed); // Output: -12

            // Convert back to zoned
            byte[] back = toZonedDecimal(new BigDecimal(parsed), 3, 0);
            for (byte b : back) {
                System.out.printf("%02X ", b);
            }
            System.out.println("\n-------------------");



            byte[] pos_9_4 = new byte[] {
                    (byte) 0xF1, // 1
                    (byte) 0xF2, // 2
                    (byte) 0xF3, // 3
                    (byte) 0xF4  // 4 (+)
            }; // ➜ 1234

            byte[] neg_9_6 = new byte[] {
                    (byte) 0xF9, // 9
                    (byte) 0xF8, // 8
                    (byte) 0xF7, // 7
                    (byte) 0xF6, // 6
                    (byte) 0xF5, // 5
                    (byte) 0xD4  // 4 (-)
            }; // ➜ -987654

            byte[] pos_3v2 = new byte[] {
                    (byte) 0xF1, // 1
                    (byte) 0xF2, // 2
                    (byte) 0xF3, // 3
                    (byte) 0xF4, // 4
                    (byte) 0xF5  // 5 (+)
            }; // ➜ 123.45 (decimalPlaces = 2)

            byte[] neg_3v2 = new byte[] {
                    (byte) 0xF1, // 1
                    (byte) 0xF2, // 2
                    (byte) 0xF3, // 3
                    (byte) 0xF4, // 4
                    (byte) 0xD5  // 5 (-)
            }; // ➜ -123.45 (decimalPlaces = 2)

            print(pos_9_4,0,true);
            print(pos_3v2,2,false);
            print(neg_9_6,0,true);
            print(neg_3v2,2,true);
        }
    }

