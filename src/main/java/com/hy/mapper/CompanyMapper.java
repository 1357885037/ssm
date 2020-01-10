package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.bean.DeptBean;
import com.hy.bean.EmpBean;
import com.hy.dao.ComDao;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import java.util.List;

@Mapper
public interface CompanyMapper extends BaseMapper<EmpBean> {
    //查询单个
    @Results({
            @Result(property = "deptBean", column = "did", one = @One(select = "getDnameById", fetchType = FetchType.EAGER))
    })
    @SelectProvider(type = ComDao.class,method = "UnSelect")
    public List<EmpBean> selectAllEmp(EmpBean empBean);

    @Select("select * from dept where did=#{value}")
    public DeptBean getDnameById();

    //为添加查询dept
    @Select("select * from dept")
    public List<DeptBean> addEmp();

    //添加
    @Insert("insert into emp(empname,sex,did) values(#{empname},#{sex},#{did})")
    public void insertEmp(EmpBean empBean);

    //删除
    @Delete("delete from emp where empid=#{value}")
    public void deleteEmp(Integer empid);

    //修改查询
    @Select("select * from emp where empid=#{value}")
    public EmpBean updateEmpOne(Integer empid);
    //修改
    @Update("update emp set empname=#{empname},sex=#{sex},did=#{did} where empid=#{empid}")
    public void updateEmp(EmpBean empBean);

    @Select("select * from emp where empid=#{value}")
    public EmpBean queryById(String id);
}
