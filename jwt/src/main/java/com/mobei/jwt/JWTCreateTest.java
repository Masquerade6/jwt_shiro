package com.mobei.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTCreateTest {
    public static void main(String[] args) {
//        JwtBuilder builder = Jwts.builder()
//                .setId("888")
//                .setSubject("小白")
//                .setIssuedAt(new Date())//设置签名时间
//                .signWith(SignatureAlgorithm.HS256, "mobei");//设置签名,防篡改:签名算法+私钥
//        System.out.println(builder.compact());

        //设置自定义数据
        JwtBuilder builder = Jwts.builder()
                .setId("888")
                .setSubject("小白")
                .setIssuedAt(new Date())//设置签名时间
                .claim("name", "张三")
                .claim("age", "20")
                .signWith(SignatureAlgorithm.HS256, "mobei");//设置签名,防篡改:签名算法+私钥
        System.out.println(builder.compact());



    }
}
