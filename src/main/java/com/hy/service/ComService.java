package com.hy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hy.bean.DeptBean;
import com.hy.bean.EmpBean;
import com.hy.mapper.CompanyMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ComService {

    @Autowired
    CompanyMapper companyMapper;

    //queryWrapper测试
    public void queryWrapper() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("empname", "一");
        List<EmpBean> list = companyMapper.selectList(queryWrapper);
        for (EmpBean empBean : list) {
            System.out.println(empBean.getEmpname());
        }
    }

    //查询
    public List<EmpBean> selectAllEmp(EmpBean empBean, int page, int limit) {
        Page page1 = PageHelper.startPage(page, limit);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!StringUtils.isNotEmpty(empBean.getEmpname())) {
            queryWrapper.like("empname", empBean.getEmpname());
        }
        if (empBean.getSex() != null) {
            queryWrapper.like("sex", empBean.getSex());
        }
        return companyMapper.selectList(queryWrapper);
    }

    //查询
    public List<EmpBean> selectEmp(EmpBean empBean, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        Page page1 = PageHelper.startPage(page, limit);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!com.mysql.jdbc.StringUtils.isNullOrEmpty(empBean.getEmpname())) {
            queryWrapper.like("empname", empBean.getEmpname());
        }
        if (empBean.getSex() != null && empBean.getSex() != -1) {
            queryWrapper.eq("sex", empBean.getSex());
        }
        List<EmpBean> list = companyMapper.selectList(queryWrapper);
        return list;
    }

    public List<EmpBean> selectEmp2(EmpBean empBean) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!com.mysql.jdbc.StringUtils.isNullOrEmpty(empBean.getEmpname())) {
            queryWrapper.like("empname", empBean.getEmpname());
        }
        if (empBean.getSex() != null && empBean.getSex() != -1) {
            queryWrapper.eq("sex", empBean.getSex());
        }
        return companyMapper.selectList(queryWrapper);
    }

    //为添加查询dept
    public List<DeptBean> addEmp() {
        return companyMapper.addEmp();
    }

    //添加
    public void insertEmp(EmpBean empBean) {
        companyMapper.insert(empBean);
        /*companyMapper.insertEmp(empBean);*/
    }

    //删除
    public void deleteEmp(Integer empid) {
        companyMapper.deleteEmp(empid);
    }

    //批量删除
    public void batchDel(String str) {
        companyMapper.deleteBatchIds(Arrays.asList(str.split(",")));
    }

    //修改查询
    public EmpBean updateEmpOne(Integer empid) {
        return companyMapper.updateEmpOne(empid);
    }

    //修改
    public void updateEmp(EmpBean empBean) {
        companyMapper.updateEmp(empBean);
    }

    //上传excel
    public void uploadExcel(MultipartFile pictureFile) {
        List<EmpBean> list = new ArrayList<>();
        InputStream in = null;
        try {
            in = pictureFile.getInputStream();
            Workbook workbook = WorkbookFactory.create(in);//Excel
            Sheet sheet = workbook.getSheetAt(0);//页  下标从0开始
            int rowNum = sheet.getLastRowNum();//获取最后一行
            for (int i = 1; i <= rowNum; i++) {
                EmpBean empBean = new EmpBean();
                Row row = sheet.getRow(i);//行
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);//获取单元格  下标从零开始
                    switch (j) {
                        case 0:
                            String str = cell.getStringCellValue();//获取String
                            empBean.setEmpname(str);
                            break;
                        case 1:
                            double dou = cell.getNumericCellValue();//获取数字
                            String sex = String.valueOf(dou);//转String类型
                            sex = sex.substring(0, sex.length() - 2);//截取
                            empBean.setSex(Integer.parseInt(sex));//存值
                            break;
                        case 2:
                            double doub = cell.getNumericCellValue();
                            String did = String.valueOf(doub);
                            did = did.substring(0, did.length() - 2);
                            empBean.setDid(Integer.parseInt(did));
                            break;
                    }
                }
                list.add(empBean);
            }
            for (EmpBean empBean : list) {
                companyMapper.insert(empBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Workbook exportExcel(EmpBean empBean) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!com.mysql.jdbc.StringUtils.isNullOrEmpty(empBean.getEmpname())) {
            queryWrapper.like("empname", empBean.getEmpname());
        }
        if (empBean.getSex() != null && empBean.getSex() != -1) {
            queryWrapper.eq("sex", empBean.getSex());
        }
        List<EmpBean> list = companyMapper.selectList(queryWrapper);
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("员工信息表");
        Row row = sheet.createRow(0);
        Cell cell1 = row.createCell(0);
        cell1.setCellValue("姓名");
        Cell cell2 = row.createCell(1);
        cell2.setCellValue("性别");
        Cell cell3 = row.createCell(2);
        cell3.setCellValue("部门");
        for (int i = 0; i < list.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            Cell cell4 = row1.createCell(0);
            cell4.setCellValue(list.get(i).getEmpname());
            Cell cell5 = row1.createCell(1);
            if (list.get(i).getSex() == 1) {
                cell5.setCellValue("女");
            }else if(list.get(i).getSex() == 2){
                cell5.setCellValue("男");
            }
            Cell cell6 = row1.createCell(2);
            if (list.get(i).getDid() == 1) {
                cell6.setCellValue("背锅部");
            }else if(list.get(i).getDid() == 2){
                cell6.setCellValue("扛刀部");
            }else if (list.get(i).getDid()==3){
                cell6.setCellValue("财务");
            }else if (list.get(i).getDid()==6){
                cell6.setCellValue("人事");
            }
        }
        return workbook;
    }

    public void ceshi() {
        EmpBean empBean = new EmpBean();
        empBean.setEmpname("周末2");
        empBean.setSex(1);
        empBean.setDid(1);
        companyMapper.insert(empBean);
    }

}
