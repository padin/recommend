package com.wyizd.recommend.controller;


import com.wyizd.recommend.dto.condition.OrganazationCondition;
import com.wyizd.recommend.entity.Organazation;
import com.wyizd.recommend.service.OrganazationService;
import com.wyizd.recommend.utils.Result;
import org.apache.tomcat.jni.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author vio1et
 * @since 2022-03-31
 */
@RestController
@RequestMapping("/recommend/organazation")
public class OrganazationController {

    @Autowired
    OrganazationService os;

    @RequestMapping("/create")
    public Result create(Principal principal, @RequestBody OrganazationCondition oc){
        try {
            os.create(principal,oc);
            return Result.succ();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping("/info")
    public Result info(@RequestBody OrganazationCondition oc){
        try {
           Organazation o = os.getById(oc.getId());
            return Result.succ(o);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping("/setTemplate")
    public Result setTemplate(Principal principal,@RequestBody OrganazationCondition oc){
        try {
            os.setTemplate(principal,oc);
            return Result.succ();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
    }


}

