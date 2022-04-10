package com.wyizd.recommend.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wyizd.recommend.dto.vo.Deal;
import com.wyizd.recommend.entity.Coupon;
import com.wyizd.recommend.entity.User;
import com.wyizd.recommend.mapper.CouponMapper;
import com.wyizd.recommend.mapper.UserMapper;
import com.wyizd.recommend.service.CouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 优惠卷 服务实现类
 * </p>
 *
 * @author vio1et
 * @since 2022-03-31
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {
    @Autowired
    CouponMapper cm;
    @Autowired
    UserMapper um;

    @Override
    public List<Coupon> listCouponByCustomer(String userId) {
        LambdaQueryWrapper<Coupon> clqw = new LambdaQueryWrapper();
        clqw.eq(Coupon::getUserId,userId);
        return cm.selectList(clqw);
    }

    /**
     * 检票
     * @param deal
     */
    @Override
    public void confirmDeal(Deal deal) throws Exception {
        Coupon coupon = cm.selectById(deal.getCouponID());
        if (coupon.getIsUsed()) {
            throw new Exception("该票已经使用过") ;
        }
        LambdaQueryWrapper<User> ulqw = new LambdaQueryWrapper<>();
        ulqw.eq(User::getPhoneNumber,deal.getAdminPhoneNumber());
        User admin = um.selectOne(ulqw);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(deal.getPassword(),admin.getPassword())) {
           throw new Exception("密码错误") ;
        }
        coupon.setIsUsed(true);
        coupon.setValidateTime(LocalDateTime.now());
        coupon.setVerifier(admin.getId());
        cm.updateById(coupon);
        admin.setLastLoginTime(LocalDateTime.now());
        um.updateById(admin);
    }



}
