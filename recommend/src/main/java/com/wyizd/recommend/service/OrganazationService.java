package com.wyizd.recommend.service;

import com.wyizd.recommend.dto.condition.OrganazationCondition;
import com.wyizd.recommend.entity.Organazation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.security.Principal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author vio1et
 * @since 2022-03-31
 */
public interface OrganazationService extends IService<Organazation> {

    void create(Principal principal, OrganazationCondition oc);

    void setTemplate(Principal principal, OrganazationCondition oc);
}
