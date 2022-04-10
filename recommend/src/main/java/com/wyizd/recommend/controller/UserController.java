package com.wyizd.recommend.controller;


import com.wyizd.recommend.dto.condition.UserCondition;
import com.wyizd.recommend.entity.Coupon;
import com.wyizd.recommend.entity.User;
import com.wyizd.recommend.service.UserService;
import com.wyizd.recommend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author vio1et
 * @since 2022-03-31
 */
@RestController
@RequestMapping("/recommend/user")
public class UserController {

    @Autowired
    UserService us;


    @PostMapping("/getCustomerByCouponId")
    public Result getCustomerByCouponId(String couponId){
        try {
            User u = us.getCustomerByCouponId(couponId);
            return Result.succ(u);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping("createReferees")
    public Result createReferees(Principal principal,@RequestBody User user){

        try {
            us.createReferees(principal,user);
            return Result.succ();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
    }
    @RequestMapping("/createCustomer")
    public Result createCustomer(@RequestBody User user){
        try {
            User u = us.createCustomer(user);
            return Result.succ(u);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
    }


    @RequestMapping("/listReferees")
    public Result listReferees(Principal principal){
        try {
            List<HashMap<String,Object>> map = us.listReferees(principal);
            return Result.succ(map);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping("/setPassword")
    public Result setPassword(Principal principal,@RequestBody UserCondition uc){
        try {
            us.setPassword(principal,uc);
            return Result.succ();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping("/listCustomer")
    public Result listCustomer(Principal principal,@RequestBody UserCondition uc){

        try {
            List<User> users = us.listCustomer(principal,uc);
            return Result.succ(users);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping("/getCustomerInfo")
    public Result getCustomerInfo(Principal principal,@RequestBody UserCondition uc){
        try {
            User user = us.getCustomerInfo(principal,uc);
            return Result.succ(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
    }








}

