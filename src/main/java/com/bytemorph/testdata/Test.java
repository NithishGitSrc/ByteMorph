package com.bytemorph.testdata;

import com.bytemorph.util.ALPHA;
import com.bytemorph.util.COMP;
import com.bytemorph.util.COMP3;
import com.bytemorph.util.NumField;



public class Test {
    public static void main(String[] args) {

        // pic x(n)
        byte[] alphaBytes = new byte[]{ (byte) 0xD5, (byte) 0xC9, (byte) 0xE3, (byte)0xC8 };
        ALPHA.decode(alphaBytes);

        System.out.println("-----------------------------------------");
        System.out.println("-----------------NUMERIC-----------------");

        //pic 9(4) 1234
        System.out.println("pic 9(4) :"+NumField.decode(TestData.pos_9_4,0,false));

        //pic 9(3)v(2) +123.45
        System.out.println("pic 9(3)v(2) :"+NumField.decode(TestData.pos_3v2,2,true));

        //pic s9(6) -987654
        System.out.println("pic s9(6) :"+NumField.decode(TestData.neg_9_6,0,true));

        //pic s9(4)v(2) -0123.45
        System.out.println("pic s9(4)v(2) :"+NumField.decode(TestData.neg_4v2,2,true));

        System.out.println("-----------------------------------------");
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

        System.out.println("Max 2-byte COMP: " + COMP.decode(pos_9_5_comp));   // 32767
        System.out.println("Max 4-byte COMP: " + COMP.decode(pos_4_byte_comp));   // 2147483647
        System.out.println("Max 8-byte COMP: " + COMP.decode(pos_8_byte_comp));   // 922337203685477580

        pos_9_5_comp = new byte[] { (byte) 0xCF, (byte) 0xC7 }; // -12345
        System.out.println("2-byte COMP: " + COMP.decode(pos_9_5_comp)); // ✅ -12345


        Number positiveValue = COMP.decode(TestData.pos_s9_5_comp);
        Number negativeValue = COMP.decode(TestData.neg_s9_5_comp);

        System.out.println("COBOL S9(5) COMP 1223 -> Java int: " + positiveValue);
        System.out.println("COBOL S9(5) COMP -456 -> Java int: " + negativeValue);



        positiveValue = (int) COMP.decode(TestData.positive4321,0);

        double negativeVal =  COMP.decode(TestData.neg_s9_4_v2_comp,2);

        System.out.println("COBOL S9(4) COMP 4321 -> Java int: " + positiveValue);
        System.out.println("COBOL S9(4) COMP -43.21 -> Java int: " + negativeVal);
        System.out.println("~~~~~~~~~~~~~~xxxxxxxxxxx~~~~~~~~~~~~~~~~~");


        System.out.println("==== COMP-3 Test ====");


        System.out.println("Mocked +1234: " + COMP3.decode(TestData.positive1234, 0));
//        System.out.println("Mocked -1234: " + decode(negative1234, 0));
        System.out.println("9(5) COMP-3: " + COMP3.decode(TestData.unsignedInt, 0));
        System.out.println("9(3)V9(2) COMP-3: " + COMP3.decode(TestData.impliedDecimal, 2)); // 2 decimal places
        System.out.println("S9(4)V9(1) COMP-3: " + COMP3.decode(TestData.signedDecimal, 1)); // 1 decimal place
        System.out.println("S9(5) COMP-3 (+): " + COMP3.decode(TestData.signedPositive, 0));
        System.out.println("S9(5) COMP-3 (-): " + COMP3.decode(TestData.signedNegative, 0));
        System.out.println("9(6) COMP-3: " + COMP3.decode(TestData.unsigned6, 0));
    }

}