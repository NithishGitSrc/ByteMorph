package org.example.bytemorph.utility;

import java.io.UnsupportedEncodingException;

public class ALPHA {

    public static String decode(byte[] bytes){
        String data ;
        try {
            data = new String(bytes,"CP1047");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(data);
        return data;
    }
    public static void test(){

        System.out.println("----------#TESTING ALPHA#----------");
        byte[] dataByte = new byte[]{ (byte) 0xD5, (byte) 0xC9, (byte) 0xE3, (byte)0xC8 };


        String data = null;
        try {
            data = new String(dataByte,"CP1047");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(data);

        System.out.println("~~~~~~~~~~~~~~xxxxxxxxxxx~~~~~~~~~~~~~~~~~");
    }
}
