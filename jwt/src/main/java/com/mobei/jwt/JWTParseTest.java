package com.mobei.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTParseTest {
    public static void main(String[] args) {
//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE1ODM1NzE1Nzh9.UNgoqAy_s_VqFaK5bMWhaapm6Bz2jb9z9WJHVFr930U";
//        Claims claims = Jwts.parser()
//                .setSigningKey("mobei")//解密需要设置签名的私钥
//                .parseClaimsJws(token)
//                .getBody();
//
//        System.out.println("id: " + claims.getId());
//        System.out.println("subject: " + claims.getSubject());
//        System.out.println("issuedAt" + claims.getIssuedAt());


        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE1ODM1NzMxNTksIm5hbWUiOiLlvKDkuIkiLCJhZ2UiOiIyMCJ9.SqIofCThZCm8vZxpzuC97GQyMxP5VKCI4bRjHwV74YA";
        Claims claims = Jwts.parser()
                .setSigningKey("mobei")//解密需要设置签名的私钥
                .parseClaimsJws(token)
                .getBody();

        System.out.println("id: " + claims.getId());
        System.out.println("subject: " + claims.getSubject());
        System.out.println("issuedAt: " + claims.getIssuedAt());
        System.out.println("name: " + claims.get("name"));
        System.out.println("age: " + claims.get("age"));
    }
}
