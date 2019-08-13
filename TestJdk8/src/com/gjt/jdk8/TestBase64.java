package com.gjt.jdk8;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

public class TestBase64 {
    public static void main(String args[]){
        try {
          
           String baseStr="hello??java8";
           // 使用基本编码
           String base64encodedString = Base64.getEncoder().encodeToString(baseStr.getBytes("utf-8"));
           System.out.println("Base64 编码字符 (基本) :" + base64encodedString);
          
           // 解码
           byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
           System.out.println("原始字符: " + new String(base64decodedBytes, "utf-8"));
           
           base64encodedString = Base64.getUrlEncoder().encodeToString(baseStr.getBytes("utf-8"));
           System.out.println("Base64 编码字符 (URL) :" + base64encodedString);
          
           String mimeEncodedString = Base64.getMimeEncoder().encodeToString(base64decodedBytes);
           System.out.println("Base64 编码字符 (MIME) :" + mimeEncodedString);
           
        }catch(UnsupportedEncodingException e){
           System.out.println("Error :" + e.getMessage());
        }
     }
}
