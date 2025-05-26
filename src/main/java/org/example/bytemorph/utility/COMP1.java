package org.example.bytemorph.utility;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class COMP1 {


        public static void main(String[] args) {
            // Stubbed COMP-1 bytes for +123.45 and -123.45
            // Float 123.45 in IEEE 754 = 0x42F6E979
            byte[] posComp1 = new byte[]{0x42, (byte) 0xF6, (byte) 0xE9, 0x79};

            // Float -123.45 in IEEE 754 = 0xC2F6E979
            byte[] negComp1 = new byte[]{(byte) 0xC2, (byte) 0xF6, (byte) 0xE9, 0x79};

            // Decode COMP-1
            float posValue = decodeComp1(posComp1);
            float negValue = decodeComp1(negComp1);

            // Display results
            System.out.println("Stubbed bytes for +123.45: " + Arrays.toString(posComp1));
            System.out.println("Decoded COMP-1 (+123.45): " + posValue);

            System.out.println("Stubbed bytes for -123.45: " + Arrays.toString(negComp1));
            System.out.println("Decoded COMP-1 (-123.45): " + negValue);
        }

        public static float decodeComp1(byte[] data) {
            if (data.length != 4) {
                throw new IllegalArgumentException("COMP-1 must be 4 bytes");
            }
            ByteBuffer bb = ByteBuffer.wrap(data);
            bb.order(ByteOrder.BIG_ENDIAN);  // mainframe uses big-endian
            int bits = bb.getInt();
            return Float.intBitsToFloat(bits);
        }

}
