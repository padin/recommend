package com.wyizd.recommend.utils;

import java.util.Random;

public class RandomUtil {
    public static Random random = new Random();
    public static String getInt(int count){
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < count; j++) {
            int i = random.nextInt(10);
            sb.append(i);
        }
        return sb.toString();
    }



    public static int getYear(){
        int i = random.nextInt(50) + 1970;
        return i;
    }

}
