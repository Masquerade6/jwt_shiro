package com.mobei.jwt;

import com.mobei.jwt.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TestController {

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/getToken")
    public String getToken() {
        return jwtUtils.createJwt("123456", "lisi", new HashMap<>());
    }
}
