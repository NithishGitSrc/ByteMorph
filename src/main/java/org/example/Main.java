package org.example;

import org.example.bytemorph.testdata.TestData;
import org.example.bytemorph.utility.ALPHA;
import org.example.bytemorph.utility.COMP;
import org.example.bytemorph.utility.NumField;


public class Main {
    public static void main(String[] args) {

        // pic x(n)
        byte[] alphaBytes = new byte[]{ (byte) 0xD5, (byte) 0xC9, (byte) 0xE3, (byte)0xC8 };
        ALPHA.decode(alphaBytes);

        System.out.println("-----------------------------------------");
        System.out.println("-----------------NUMERIC-----------------");

        //pic 9(4) 1234
        NumField.decode(TestData.pos_9_4,0,false);

        //pic 9(3)v(2) +123.45
        NumField.decode(TestData.pos_3v2,2,true);

        //pic s9(6) -987654
        NumField.decode(TestData.neg_9_6,0,true);

        //pic s9(4)v(2) -0123.45
        NumField.decode(TestData.neg_4v2,2,true);

        System.out.println("-----------------------------------------");
        System.out.println("-------------------COMP------------------");

        System.out.println(COMP.decodeComp(TestData.pos_9_5_comp));   // 32767
        System.out.println(COMP.decodeComp(TestData.pos_4_byte_comp));   // 2147483647
        System.out.println(COMP.decodeComp(TestData.pos_8_byte_comp));   // 922337203685477580
        System.out.println(COMP.decodeComp(TestData.pos_s9_5_comp));
        System.out.println(COMP.decodeComp(TestData.positive4321));
        System.out.println(COMP.decodeComp(TestData.neg_s9_5_comp));
        System.out.println(COMP.decodeComp(TestData.neg_s9_4_v2_comp,2));



    }

}