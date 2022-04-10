package com.wyizd.recommend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 优惠卷
 * </p>
 *
 * @author vio1et
 * @since 2022-03-31
 */
@Getter
@Setter
@TableName("t_coupon")
@ApiModel(value = "Coupon对象", description = "优惠卷")
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("是否使用")
    @TableField("is_used")
    private Boolean isUsed;

    @ApiModelProperty("验证人")
    @TableField("verifier")
    private String verifier;

    @ApiModelProperty("验证时间")
    @TableField("validate_time")
    private LocalDateTime validateTime;

    @ApiModelProperty("二维码地址")
    @TableField("er_code_address")
    private String erCodeAddress;

    @ApiModelProperty("描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("价格")
    @TableField("price")
    private Double price;

    @ApiModelProperty("有效期（开始")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("有效期（结束")
    @TableField("validity_time")
    private LocalDateTime validityTime;

    @ApiModelProperty("模版编号")
    @TableField("template_id")
    private String templateId;


    @ApiModelProperty("用户编号")
    @TableField("user_id")
    private String userId;


}
