package com.mobei.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties("jwt.config")
@Component
public class JwtUtils {
    //签名私钥
    private String key;
    //签名的失效时间
    private Long ttl;

    /**
     * 设置认证token
     * @param id 登录用户id
     * @param name 登录用户名
     * @param map 其他参数
     * @return token
     */
    public String createJwt(String id, String name, Map<String, Object> map) {
        //1.设置失效时间
        long now = System.currentTimeMillis();//当前毫秒
        long exp = now + ttl;

        //2.创建JwtBuilder
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(name)
                .setIssuedAt(new Date())//设置签名时间
                .signWith(SignatureAlgorithm.HS256, key);//设置签名,防篡改:签名算法+私钥

        //3.根据map设置claims
        builder.setClaims(map);

        //4.设置失效时间
        builder.setExpiration(new Date(exp));

        //4.创建token
        String token = builder.compact();

        System.out.println(token);
        return token;
    }

    /**
     * 解析token字符串获取claims
     * @param token
     * @return
     */
    public Claims parseJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(token)//解密需要设置签名的私钥
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

}
