package com.wyizd.recommend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("t_organazation")
@ApiModel(value = "Organazation对象", description = "")
public class Organazation implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("组织名称")
    @TableField("organazation_name")
    private String organazationName;

    @ApiModelProperty("组织使用有效期")
    @TableField("validity")
    private LocalDateTime validity;

    @ApiModelProperty("优惠券价格")
    @TableField("coupon_price")
    private BigDecimal couponPrice;

    @ApiModelProperty("有效期（天")
    @TableField("coupon_validity")
    private Integer couponValidity;

    @ApiModelProperty("共发行（张")
    @TableField("coupon_total")
    private Integer couponTotal;

    @ApiModelProperty("剩余（张")
    @TableField("coupon_surplus")
    private Integer couponSurplus;

    @ApiModelProperty("是否启用")
    @TableField("coupon_enable")
    private boolean couponEnable;

    @ApiModelProperty("修改人")
    @TableField("last_modify")
    private String lastModify;


}
