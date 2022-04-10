package com.wyizd.recommend.controller;


import com.wyizd.recommend.dto.vo.Deal;
import com.wyizd.recommend.entity.Coupon;
import com.wyizd.recommend.service.CouponService;
import com.wyizd.recommend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 优惠卷 前端控制器
 * </p>
 *
 * @author vio1et
 * @since 2022-03-31
 */
@RestController
@RequestMapping("/recommend/coupon")
public class CouponController {
    @Autowired
    CouponService cs;

    @PostMapping("/listCouponByCustomer")
    public Result listCouponByCustomer(String userId){
        try {
            List<Coupon> coupons = cs.listCouponByCustomer(userId);
            return Result.succ(coupons);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/getCoupon")
    public Result getCoupon(String couponId){
        try {
            Coupon c = cs.getById(couponId);
            return Result.succ(c);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
    }


    @PostMapping("/confirmDeal")
    public Result confirmDeal(@RequestBody Deal deal){
        try {
            cs.confirmDeal(deal);
            return Result.succ();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
    }

}

