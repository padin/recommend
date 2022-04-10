package com.wyizd.recommend;

import com.wyizd.recommend.utils.SMSUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TestSendSMS {
    @Test
    public void test() throws Exception {

        Map<String,String> map = new HashMap<>();
        map.put("code","876567");
        SMSUtil.send("18286010105",map);

    }
}
