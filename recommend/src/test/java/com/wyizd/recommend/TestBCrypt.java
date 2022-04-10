package com.wyizd.recommend;


import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBCrypt {

    @Test
    void testBCrypt(){
        String encode = new BCryptPasswordEncoder().encode("123456");
        System.out.println(encode);
    }

}
