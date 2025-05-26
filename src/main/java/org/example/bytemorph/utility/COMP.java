package org.example.bytemorph.utility;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;



public class COMP {

    public static void main(String []args){

        System.out.println("----------#TESTING COMP#----------");

        // Max for 2-byte COMP: 32767
        byte[] pos_9_5_comp = new byte[] { 0x7F, (byte) 0xFF };

        // Max for 4-byte COMP: 2,147,483,647
        byte[] pos_4_byte_comp = new byte[] { 0x7F, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF };

        // Max for 8-byte COMP: 9,223,372,036,854,775,807L
        byte[] pos_8_byte_comp = new byte[] {
                0x7F, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
                (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF
        };

        System.out.println("Max 2-byte COMP: " + decodeComp(pos_9_5_comp));   // 32767
        System.out.println("Max 4-byte COMP: " + decodeComp(pos_4_byte_comp));   // 2147483647
        System.out.println("Max 8-byte COMP: " + decodeComp(pos_8_byte_comp));   // 922337203685477580

        pos_9_5_comp = new byte[] { (byte) 0xCF, (byte) 0xC7 }; // -12345
        System.out.println("2-byte COMP: " + decodeComp(pos_9_5_comp)); // ✅ -12345

        byte[] pos_s9_5_comp = {(byte)0x00, (byte)0x7B}; // 123
        byte[] neg_s9_5_comp = new byte[] {(byte)0xFE, (byte)0x38}; // -456

        long positiveValue = decodeComp(pos_s9_5_comp);
        long negativeValue = decodeComp(neg_s9_5_comp);

        System.out.println("COBOL S9(5) COMP 1223 -> Java int: " + positiveValue);
        System.out.println("COBOL S9(5) COMP -456 -> Java int: " + negativeValue);


        byte[] positive4321 = new byte[]{ (byte) 0x10, (byte) 0xE1 };
        byte[] neg_s9_4_v2_comp = new byte[]{ (byte) 0xEF, (byte) 0x1F };

          positiveValue = (int) decodeComp(positive4321,0);

          double negativeVal =  decodeComp(neg_s9_4_v2_comp,2);

        System.out.println("COBOL S9(4) COMP 4321 -> Java int: " + positiveValue);
        System.out.println("COBOL S9(4) COMP -43.21 -> Java int: " + negativeVal);
        System.out.println("~~~~~~~~~~~~~~xxxxxxxxxxx~~~~~~~~~~~~~~~~~");

    }
    public static long decodeComp(byte[] data) {
        ByteBuffer bb = ByteBuffer.wrap(data);
        bb.order(ByteOrder.BIG_ENDIAN);

        switch (data.length) {
            case 2: return bb.getShort();
            case 4: return bb.getInt();
            case 8: return bb.getLong();
            default:
                throw new IllegalArgumentException("Unsupported COMP field size: " + data.length);
        }

    }

    public static double decodeComp(byte[] data, int decimalPlaces) {
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
