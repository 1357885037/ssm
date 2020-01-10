package com.hy.bean;

import java.util.List;

public class DeptBean {
private Integer did;
private String dname;

private List<EmpBean> empBean;

    public List<EmpBean> getEmpBean() {
        return empBean;
    }

    public void setEmpBean(List<EmpBean> empBean) {
        this.empBean = empBean;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}
