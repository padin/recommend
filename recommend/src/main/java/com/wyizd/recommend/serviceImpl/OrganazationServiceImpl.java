package com.wyizd.recommend.serviceImpl;

import com.wyizd.recommend.dto.condition.OrganazationCondition;
import com.wyizd.recommend.entity.Organazation;
import com.wyizd.recommend.entity.User;
import com.wyizd.recommend.enums.ROLE_TYPE;
import com.wyizd.recommend.mapper.OrganazationMapper;
import com.wyizd.recommend.mapper.UserMapper;
import com.wyizd.recommend.service.OrganazationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vio1et
 * @since 2022-03-31
 */
@Service
public class OrganazationServiceImpl extends ServiceImpl<OrganazationMapper, Organazation> implements OrganazationService {
    @Autowired
    OrganazationMapper om;
    @Autowired
    UserMapper um;
    @Value("${app.default-password}")
    String defaultPassword;

    @Override
    @Transactional
    public void create(Principal principal, OrganazationCondition oc) {
        Organazation o = new Organazation();
        o.setOrganazationName(oc.getOrganazationName());
        o.setValidity(LocalDateTime.now().plusMonths(oc.getValidityMonth()));
        o.setCouponTotal(0);
        o.setCouponSurplus(0);
        o.setCouponPrice(new BigDecimal(1888));
        o.setCouponEnable(false);
        o.setCouponValidity(30);
        om.insert(o);
        User u = new User();
        u.setOrganazationId(o.getId());
        u.setPhoneNumber(oc.getMobilephone());
        u.setRealName(oc.getRealName());
        u.setPassword(new BCryptPasswordEncoder().encode(defaultPassword));
        u.setReferees(principal.getName());
        u.setRoleType(ROLE_TYPE.ADMIN.getIndex());
        u.setCreateTime(LocalDateTime.now());
        u.setUserName(oc.getOrganazationName());
        um.insert(u);
    }

    @Override
    public void setTemplate(Principal principal, OrganazationCondition oc) {
        Organazation organazation = new Organazation();
        BeanUtils.copyProperties(oc,organazation);
        organazation.setLastModify(principal.getName());
        organazation.setCouponTotal(organazation.getCouponTotal()+oc.getAdd());
        organazation.setCouponSurplus(organazation.getCouponSurplus()+oc.getAdd());
        om.updateById(organazation);
    }
}
