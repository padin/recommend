package com.wyizd.recommend;


import com.wyizd.recommend.enums.ROLE_TYPE;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestEnum {

    @Test
    void testBCrypt(){
        System.out.println(ROLE_TYPE.ADMIN.getName(1));
    }

}
