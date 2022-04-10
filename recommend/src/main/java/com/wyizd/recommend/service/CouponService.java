package com.wyizd.recommend.service;

import com.wyizd.recommend.dto.vo.Deal;
import com.wyizd.recommend.entity.Coupon;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 优惠卷 服务类
 * </p>
 *
 * @author vio1et
 * @since 2022-03-31
 */
public interface CouponService extends IService<Coupon> {

    List<Coupon> listCouponByCustomer(String userId);

    void confirmDeal(Deal deal) throws Exception;
}
