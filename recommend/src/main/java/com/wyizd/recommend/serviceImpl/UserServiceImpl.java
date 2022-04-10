package com.wyizd.recommend.serviceImpl;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wyizd.recommend.dto.condition.UserCondition;
import com.wyizd.recommend.entity.Coupon;
import com.wyizd.recommend.entity.Organazation;
import com.wyizd.recommend.entity.User;
import com.wyizd.recommend.enums.ROLE_TYPE;
import com.wyizd.recommend.mapper.CouponMapper;
import com.wyizd.recommend.mapper.OrganazationMapper;
import com.wyizd.recommend.mapper.UserMapper;
import com.wyizd.recommend.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyizd.recommend.utils.CreateFolderSample;
import com.wyizd.recommend.utils.ErCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vio1et
 * @since 2022-03-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper um;
    @Autowired
    CouponMapper cm;
    @Autowired
    OrganazationMapper om;
    @Value("${app.default-password}")
    String defaultPassword;
    @Value("${app.er-code-path-prefix}")
    String erCodePathPrefix;
    @Value("${app.er-code-referees-prefix}")
    String erCodeRefereesPrefix;
    @Value("${app.er-code-customer-prefix}")
    String erCodeCustomerPrefix;
    @Value("${app.er-code-coupon-prefix}")
    String erCodeCouponPrefix;





    @Override
    public void createReferees(Principal principal, User user) throws Exception {
        checkPhoneExist(user.getUserName());
        User u = um.selectById(principal.getName());
        user.setReferees(principal.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(defaultPassword));
        user.setCreateTime(LocalDateTime.now());
        user.setRoleType(ROLE_TYPE.REFEREES.getIndex());
        user.setOrganazationId(u.getOrganazationId());
        um.insert(user);
        CreateFolderSample.upload(ErCodeGenerator.gen(erCodeRefereesPrefix+user.getId()),user.getId()+".jpeg");
        user.setErCodeAddress(erCodePathPrefix+user.getId()+".jpeg");
        um.updateById(user);
    }

    /**
     * 添加客户
     * 客户的二维码为客户的优惠券查询地址
     * @param user
     * @throws Exception
     * @return
     */
    @Override
    @Transactional
    public User createCustomer(User user) throws Exception {
        //添加基本信息
        checkPhoneExist(user.getPhoneNumber().toString());
        User user1 = um.selectById(user.getReferees());
        user.setCreateTime(LocalDateTime.now());
        user.setOrganazationId(user1.getOrganazationId());
        user.setIsOrder(0);
        user.setRoleType(ROLE_TYPE.CUSTOMER.getIndex());
        um.insert(user);
        //添加二维码查询信息
        CreateFolderSample.upload(ErCodeGenerator.gen(erCodeCustomerPrefix+user.getId()),user.getId()+".jpeg");
        user.setErCodeAddress(erCodePathPrefix+user.getId()+".jpeg");
        um.updateById(user);
        //生成优惠券
        Organazation o = om.selectById(user.getOrganazationId());
        if (o.getCouponSurplus()>=1){
            o.setCouponSurplus(o.getCouponSurplus()-1);
            om.updateById(o);
        }else{
            throw new Exception("优惠券不足");
        }
        Coupon c = new Coupon();
        c.setCreateTime(LocalDateTime.now());
        c.setPrice(o.getCouponPrice().doubleValue());
        c.setIsUsed(false);
        c.setValidateTime(LocalDateTime.now().plusDays(o.getCouponValidity()));
        c.setTitle(o.getOrganazationName());
        c.setDescription("下单立减,通过商家扫描二维码验证后立即生效");
        c.setTemplateId(o.getId());
        c.setUserId(user.getId());
        cm.insert(c);
        CreateFolderSample.upload(ErCodeGenerator.gen(erCodeCouponPrefix+c.getId()),c.getId()+".jpeg");
        c.setErCodeAddress(erCodePathPrefix+c.getId()+".jpeg");
        cm.updateById(c);

        return user;
    }

    @Override
    public List<HashMap<String, Object>> listReferees(Principal principal) {
        User user = um.selectById(principal.getName());
        List<HashMap<String, Object>> map = um.listReferees(user.getOrganazationId());
        return map;
    }

    /**
     * 检查手机是否使用过
     * @param phoneNumber
     */
    @Override
    public void checkPhoneExist(String phoneNumber) throws Exception {
        List<User> phone_number = um.selectByMap(MapUtil.builder(new HashMap<String, Object>()).put("phone_number", phoneNumber).build());
        if(phone_number.size()!=0){
            throw new Exception("电话号码已存在");
        }
    }

    @Override
    public void setPassword(Principal principal, UserCondition uc) throws Exception {
        User u = um.selectById(principal.getName());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(uc.getOldPassword(),u.getPassword())){
            u.setPassword(encoder.encode(uc.getNewPassword()));
        }else{
            throw new Exception("密码错误");
        }
        um.updateById(u);
    }

    @Override
    public List<User> listCustomer(Principal principal, UserCondition uc) {
//        String current_id = principal.getName();
//        User user = um.selectById(current_id);

        LambdaQueryWrapper<User> ulqw = new LambdaQueryWrapper<>();
        ulqw.eq(User::getReferees,uc.getUid());
        ulqw.eq(User::getRoleType,ROLE_TYPE.CUSTOMER.getIndex());
        if (uc.getBegin()!=null){
            ulqw.ge(User::getCreateTime,uc.getBegin());
        }
        if (uc.getEnd()!=null){
            ulqw.le(User::getCreateTime,uc.getEnd());
        }
        List<User> users = um.selectList(ulqw);

//        switch (ROLE_TYPE.gen(user.getRoleType())){
//            case ADMIN:
//            case SUPER_ADMIN:
//
//                break;
//            case REFEREES:
//
//                break;
//
//        }


        return users;
    }

    @Override
    public User getCustomerInfo(Principal principal, UserCondition uc) {
        User user = um.selectById(uc.getId());
        return user;
    }

    @Override
    public User getCustomerByCouponId(String couponId) {
        Coupon coupon = cm.selectById(couponId);
        User user = um.selectById(coupon.getUserId());
        return user;
    }

}
