package com.hy.service;

import com.hy.bean.EmpBean;
import com.hy.dao.RedisDao;
import com.hy.mapper.CompanyMapper;
import com.hy.mapper.RedisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "emp")
@EnableCaching
public class RedisService {
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private RedisMapper redisMapper;

    public void saveOrUpdateString(String k, String v) {
        redisDao.saveOrUpdateString(k, v);
    }

    public Object getString(String k) {
        return redisDao.getString(k);
    }

    //redis缓存查询
    @Cacheable(key = "'emp_'+#empid")
    public EmpBean queryById(String empid) {
        return companyMapper.selectById(empid);
    }

    //redis删除缓存
    @CacheEvict(key = "'emp_'+#empid")
    public void deleteById(int empid) {
       companyMapper.deleteById(empid);
    }

    //redis修改缓存
    @CachePut(key = "'emp_'+#empBean.empid")
    public EmpBean updateById(EmpBean empBean) {
        companyMapper.updateById(empBean);
        return empBean;
    }


    //二级缓存
    @Cacheable(key = "'emp_'+#empid")
    public EmpBean selectById(String empid) {
        return redisMapper.selectById(empid);
    }
}