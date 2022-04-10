package com.wyizd.recommend.dto.condition;

import com.wyizd.recommend.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserCondition extends User {
    LocalDateTime begin;
    LocalDateTime end;
    String uid;
    String oldPassword;
    String newPassword;
}
