package com.wyizd.recommend.enums;

import cn.hutool.json.JSONUtil;

public enum ROLE_TYPE {
    CUSTOMER(1,"客户"),REFEREES(2,"推荐人"),ADMIN(3,"管理员"),SUPER_ADMIN(4,"超级管理员");


    int index;
    String  name;

    ROLE_TYPE(int index, String name) {
        this.index = index;
        this.name = name;
    }



    public int getIndex() {
        return index;
    }

    public static ROLE_TYPE gen(int index){
        for (ROLE_TYPE value : ROLE_TYPE.values()) {
            if (value.getIndex()==index){
                return value;
            }
        }
        return null;
    }

    public String getName(int index) {
        for (ROLE_TYPE value : ROLE_TYPE.values()) {
            if (value.getIndex()==index){
                return value.name;
            }
        }
        return name;
    }

    public static void main(String[] args) {


        System.out.println(ROLE_TYPE.gen(1));
    }


}
