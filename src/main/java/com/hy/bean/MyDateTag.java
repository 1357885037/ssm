package com.hy.bean;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class MyDateTag extends SimpleTagSupport {
    String url;
    String param;
    Integer dangqian;
    Integer zhongyeshu;
    String empname;
    String empname_value;
   Integer sex;
   Integer sex_value;
    Integer did;
    Integer did_value;

    public void doTag() throws JspException, IOException {

        Integer prePage = dangqian - 1 > 0 ? dangqian - 1 : dangqian;
        Integer nextPage = dangqian + 1 > zhongyeshu ? dangqian : dangqian + 1;
        String urlp = url + "?" + param + "=" + prePage + "";
        String urln = url + "?" + param + "=" + nextPage + "";

        if (null != empname && !"".equals(empname) && null != empname_value && !"".equals(empname_value)) {
            urlp += "&" + empname + "=" + empname_value;
            urln += "&" + empname + "=" + empname_value;
        }

        if (null != sex && !"".equals(sex) && null != sex_value && !"".equals(sex_value)) {
            urlp += "&" + sex + "=" + sex_value;
            urln += "&" + sex + "=" + sex_value;
        }

        if (null != did && !"".equals(did) && null != did_value && !"".equals(did_value)) {
            urlp += "&" + did + "=" + did_value;
            urln += "&" + did + "=" + did_value;
        }

        StringBuilder sb = new StringBuilder();
        String str = "";
        sb.append("<a href='" + urlp + "'>上一页</a>");
        for (int i = 1; i <= zhongyeshu; i++) {
            String urle = url + "?" + param + "=" + i + "";
            str = "<a href='" + urle + "'>" + i + "</a>";
            sb.append(str);
        }
        sb.append("<a href='" + urln + "'>下一页 </a>");
        getJspContext().getOut().write(sb.toString());

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Integer getDangqian() {
        return dangqian;
    }

    public void setDangqian(Integer dangqian) {
        this.dangqian = dangqian;
    }

    public Integer getZhongyeshu() {
        return zhongyeshu;
    }

    public void setZhongyeshu(Integer zhongyeshu) {
        this.zhongyeshu = zhongyeshu;
    }

    public String getempname() {
        return empname;
    }

    public void setempname(String empname) {
        this.empname = empname;
    }

    public String getempname_value() {
        return empname_value;
    }

    public void setempname_value(String empname_value) {
        this.empname_value = empname_value;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getEmpname_value() {
        return empname_value;
    }

    public void setEmpname_value(String empname_value) {
        this.empname_value = empname_value;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSex_value() {
        return sex_value;
    }

    public void setSex_value(Integer sex_value) {
        this.sex_value = sex_value;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getDid_value() {
        return did_value;
    }

    public void setDid_value(Integer did_value) {
        this.did_value = did_value;
    }
}

