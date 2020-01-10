package com.hy.controller;

import com.github.pagehelper.PageInfo;
import com.hy.bean.DeptBean;
import com.hy.bean.EmpBean;
import com.hy.bean.Upload;
import com.hy.dao.LayUi;
import com.hy.service.ComService;
import io.swagger.annotations.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Api("学生信息")
@Controller
public class ComController {

    @Autowired
    ComService comService;

    //查询
    @ApiOperation(httpMethod = "get", value = "学生信息查询")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "查询成功", response = EmpBean.class),
            @ApiResponse(code = 500, message = "查询失败")
    })
    @ResponseBody
    @RequestMapping(value = "/selectEmp.do",method = RequestMethod.GET )
    public LayUi selectEmp(EmpBean empBean, @ApiParam(required = true,name = "page",value = "页数") @RequestParam("page") Integer page,@ApiParam(required = true,name = "limit",value = "一页几条")  @RequestParam("limit") Integer limit) {
        List<EmpBean> list = comService.selectEmp(empBean, page, limit);
        LayUi layUi = new LayUi();
        layUi.setCode(0);
        layUi.setMsg("");
        layUi.setCount(comService.selectEmp2(empBean).size());
        layUi.setData(list);
        return layUi;
    }

    //为添加查询dept
    @ApiOperation(httpMethod = "get", value = "为添加学生信息查询班级")
    @RequestMapping(value = "/addEmp.do",method = RequestMethod.GET )
    public String addEmp(Model model) {
        List<DeptBean> d = comService.addEmp();
        model.addAttribute("d", d);
        return "/addEmp";
    }

    //添加emp
    @ApiOperation(httpMethod = "get", value = "添加学生信息")
    @RequestMapping(value = "/insertEmp.do",method = RequestMethod.GET )
    public String insertEmp(EmpBean empBean) {
        comService.insertEmp(empBean);
        return "";
    }

    //修改查询
    @ApiOperation(httpMethod = "get", value = "修改")
    @RequestMapping(value = "/updateEmpOne.do",method = RequestMethod.GET )
    public String updateEmpOne(Model model, @ApiParam(required = true,name = "empid",value = "学生ID")Integer empid) {
        EmpBean emp = comService.updateEmpOne(empid);
        model.addAttribute("up", emp);
        List<DeptBean> dept = comService.addEmp();
        model.addAttribute("dept", dept);
        return "/updates";
    }

    //修改
    @ApiOperation(httpMethod = "get", value = "修改")
    @ResponseBody
    @RequestMapping(value = "/updateEmp.do",method = RequestMethod.GET )
    public String updateEmp(EmpBean empBean) {
        comService.updateEmp(empBean);
        return "";
    }

    //删除
    @ApiOperation(httpMethod = "get", value = "删除")
    @ResponseBody
    @RequestMapping(value = "/deleteEmp.do",method = RequestMethod.GET )
    public Integer deleteEmp(@ApiParam(required = true,name = "empid",value = "学生ID")Integer empid) {
        Integer i = 1;
        try {
            comService.deleteEmp(empid);
        } catch (Exception e) {
            i = 2;
        }
        return i;
    }

    //批量删除
    @ApiOperation(httpMethod = "get", value = "批量删除")
    @ResponseBody
    @RequestMapping(value = "/batchDel.do",method = RequestMethod.GET )
    public String batchDel(String str) {
        String i = "1";
        try {
            comService.batchDel(str);
        } catch (Exception e) {
            i = "2";
        }
        return i;
    }

    // 图片上传
    @ApiOperation(httpMethod = "get", value = "上传图片")
    @ResponseBody
    @RequestMapping(value = "/uploadPhoto.do",method = RequestMethod.GET )
    public Upload uploadPhoto(@RequestParam("file") MultipartFile pictureFile, HttpServletRequest request) {
        // 设置图片名称，不能重复，可以使用uuid
        String picName = UUID.randomUUID().toString();
        // 获取文件名
        String oriName = pictureFile.getOriginalFilename();
        // 获取图片后缀
        String extName = oriName.substring(oriName.lastIndexOf("."));
        try {
            //获取根路径
            String req = request.getSession().getServletContext().getRealPath("/");
            System.out.println(req);
            // 开始上传
            pictureFile.transferTo(new File(req + "\\imgs\\" + picName + extName));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Upload up = new Upload();
        up.setCode("0");
        up.setFilename(picName + extName);
        return up;
    }

    @ApiOperation(httpMethod = "get", value = "base")
    @RequestMapping("/base.do")
    public String base() {
        return "/empDept";
    }


    //上传excel
    @ApiOperation(httpMethod = "get", value = "导入Excel")
    @ResponseBody
    @RequestMapping(value = "/uploadExcel.do",method = RequestMethod.GET )
    public Integer uploadExcel(@RequestParam("file") MultipartFile pictureFile) {
        Integer i = 1;
        try {
            comService.uploadExcel(pictureFile);
        } catch (Exception e) {
            i = 2;
        }
        return 1;
    }

    //导出excel
    @ApiOperation(httpMethod = "get", value = "导出Excel")
    @RequestMapping(value = "/exportExcel.do",method = RequestMethod.GET)
            public void exportExcel(HttpServletResponse response, EmpBean empBean) throws IOException {
        response.setContentType("application/x-excel");
        response.setHeader("content-disposition", "attachment;fileName=export.xls");
        ServletOutputStream outputStream = response.getOutputStream();
        Workbook workbook = comService.exportExcel(empBean);
        workbook.write(outputStream);
    }

    @ApiOperation(httpMethod = "get", value = "测试")
    @RequestMapping(value = "/abc.do",method = RequestMethod.GET )
    public String abc() {
        comService.ceshi();
        return null;
    }

    @ApiOperation(httpMethod = "get", value = "查询学生信息")
    @RequestMapping(value = "/selectAllEmp.do",method = RequestMethod.GET )
    public String selectAllEmp(Model model, EmpBean empBean,@ApiParam(required = true,name = "dangqian",value = "当前页") @RequestParam(value = "dangqian", defaultValue = "0") int dangqian,@ApiParam(required = true,name = "pagesize",value = "总页数") @RequestParam(value = "pagesize", defaultValue = "5") int pagesize) {
        List<EmpBean> list = comService.selectAllEmp(empBean, dangqian, pagesize);
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("list", list);
        model.addAttribute("empBean", empBean);
        model.addAttribute("pageInfo", pageInfo);
        return "/empdept";
    }
}
