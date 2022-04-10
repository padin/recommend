package com.wyizd.recommend;

import com.wyizd.recommend.entity.User;
import com.wyizd.recommend.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    void testSelect() {
        List<User> users = userMapper.selectList(null);
        users.forEach(i->{
            System.out.println(i);
        });
    }

    @Test
    void testInsert() {
        User user = new User();
        user.setId("18888998899");
        user.setAddress("a");
        user.setBuildPhase(1);
        user.setRoleType(2);
        int insert = userMapper.insert(user);
        System.out.println(insert);
        System.out.println(user.getId());
    }

    @Test
    void testDeleteById() {
        User user = new User();
        user.setId("18888998899");
        int insert = userMapper.deleteById(user);
        System.out.println(insert);
        System.out.println(user.getId());
    }

    @Test
    void testUpdateById() {
        User user = new User();
        user.setId("18888998899");
        user.setAddress("iuiouiouoi");
        int insert = userMapper.updateById(user);
        System.out.println(insert);
        System.out.println(user.getId());
    }


}
