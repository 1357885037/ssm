package com.hy.dao;

import com.hy.bean.EmpBean;

public class ComDao {
    public String UnSelect(EmpBean empBean) {
        StringBuffer sql = new StringBuffer("select * from emp where 1=1 ");
        if (empBean.getEmpname() != null && empBean.getEmpname() != "") {
            sql.append(" and empname like '%" + empBean.getEmpname() + "%' ");
        }
        if (empBean.getSex() != null) {
            sql.append(" and sex="+empBean.getSex()+"");
        }
        if (empBean.getDid() != null) {
            sql.append(" and did="+empBean.getDid()+"");
        }
        return sql.toString();
    }
}
