package com.wyizd.recommend;

import com.wyizd.recommend.entity.Organazation;
import com.wyizd.recommend.entity.User;
import com.wyizd.recommend.mapper.OrganazationMapper;
import com.wyizd.recommend.mapper.UserMapper;
import com.wyizd.recommend.service.OrganazationService;
import com.wyizd.recommend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class OrganazationTest {
    @Autowired
    OrganazationMapper om;
    @Autowired
    OrganazationService os;
    @Autowired
    UserMapper um;
    @Test
    void testAddOrg(){
        User user = um.selectById("18286010105");
        Organazation o = new Organazation();
        o.setOrganazationName("尚层空间");
        o.setValidity(LocalDateTime.of(2022,12,31,0,0));
//        os.createOrganazation(o,user);

    }
    @Test
    void testListById(){
    }

}
