package com.wyizd.recommend.mapper;

import com.wyizd.recommend.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author vio1et
 * @since 2022-03-31
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<HashMap<String,Object>> listReferees(@Param("orgid") String organazationId);

}
