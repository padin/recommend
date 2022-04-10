package com.wyizd.recommend.service;

import com.wyizd.recommend.dto.condition.UserCondition;
import com.wyizd.recommend.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author vio1et
 * @since 2022-03-31
 */
public interface UserService extends IService<User> {

    void createReferees(Principal principal, User user) throws Exception;


    User createCustomer(User user) throws Exception;

    List<HashMap<String, Object>> listReferees(Principal principal);

    void checkPhoneExist(String phoneNumber) throws Exception;

    void setPassword(Principal principal, UserCondition uc) throws Exception;

    List<User> listCustomer(Principal principal, UserCondition uc);

    User getCustomerInfo(Principal principal, UserCondition uc);

    User getCustomerByCouponId(String couponId);
}
