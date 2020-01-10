package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.bean.EmpBean;
import com.hy.cache.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

@CacheNamespace(implementation = RedisCache.class)
@Mapper
public interface RedisMapper extends BaseMapper<EmpBean> {


}
