package com.wyizd.recommend.dto.condition;

import com.wyizd.recommend.entity.Organazation;
import lombok.Data;

@Data
public class OrganazationCondition extends Organazation {
    Long validityMonth;
    Long mobilephone;
    String realName;
    Integer add;
}
