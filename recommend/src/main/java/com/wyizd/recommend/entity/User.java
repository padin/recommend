package com.wyizd.recommend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author vio1et
 * @since 2022-03-31
 */
@Getter
@Setter
@TableName("t_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键")
    @TableId("id")
    private String id;

    @ApiModelProperty("用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("真实姓名")
    @TableField("real_name")
    private String realName;

    @ApiModelProperty("电话号码")
    @TableField("phone_number")
    private Long phoneNumber;

    @ApiModelProperty("二维码地址")
    @TableField("er_code_address")
    private String erCodeAddress;

    @ApiModelProperty("地址")
    @TableField("address")
    private String address;

    @ApiModelProperty("行政区")
    @TableField("area_name")
    private String areaName;

    @ApiModelProperty("社区")
    @TableField("community")
    private String community;

    @ApiModelProperty("建筑期")
    @TableField("build_phase")
    private Integer buildPhase;

    @ApiModelProperty("楼号")
    @TableField("house_number")
    private Integer houseNumber;

    @ApiModelProperty("是否下单")
    @TableField("is_order")
    private Integer isOrder;

    @ApiModelProperty("推荐人")
    @TableField("referees")
    private String referees;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("上次登陆时间")
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty("角色类型(1-客户，2-推荐人，3-管理员，4-超级管理员）")
    @TableField("role_type")
    private Integer roleType;

    @ApiModelProperty("所属单位")
    @TableField("organazation_id")
    private String organazationId;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;


}
