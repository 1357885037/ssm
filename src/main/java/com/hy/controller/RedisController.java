package com.hy.controller;

import com.hy.bean.EmpBean;
import com.hy.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RedisController {
    @Autowired
    private RedisService redisService;

    @RequestMapping("/saveOrUpdateString.do")
    public void saveOrUpdateString(String k, String v) {
        redisService.saveOrUpdateString(k, v);
    }

    @RequestMapping("/getString.do")
    public Object getString(String k) {
        return redisService.getString(k);
    }

    //redis缓存查询
    @ResponseBody
    @RequestMapping("/queryById.do")
    public EmpBean queryById(String empid) {
        return redisService.queryById(empid);
    }

    //redis删除缓存
    @ResponseBody
    @RequestMapping("/deleteById.do")
    public void deleteById(int empid) {
        redisService.deleteById(empid);


        System.out.println("测试");
    }



    //redis修改缓存
    @ResponseBody
    @RequestMapping("/updateById.do")
    public EmpBean updateById(EmpBean empBean) {
        empBean.setEmpname("小陈");
        return redisService.updateById(empBean);
    }

    //二级缓存测试
    @ResponseBody
    @RequestMapping("/selectById.do")
    public EmpBean selectById(String empid) {
        return redisService.selectById(empid);
    }

}
