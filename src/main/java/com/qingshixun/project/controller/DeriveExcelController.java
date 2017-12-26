package com.qingshixun.project.controller;

import com.qingshixun.project.model.UserModel;
import com.qingshixun.project.service.LoginService;
import com.qingshixun.project.service.QueryService;
import com.qingshixun.project.util.CreateExcel;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

@RequestMapping("user")
@Controller
public class DeriveExcelController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private CreateExcel createExcel;

    @Autowired
    private QueryService queryService;

    private int maxPage=5;

    //导出文件
    @RequestMapping(value = "getexcel")
    public void deriveExcel(HttpServletResponse response,int pageNo) {

        List<UserModel> list = loginService.loginPass((pageNo-1)*maxPage,maxPage);
        XSSFWorkbook wb = createExcel.createExcel(pageNo,list);
        try {
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            String filename =UUID.randomUUID().toString();

//            解决中文名称乱码
            String newfilename = new String(filename.getBytes("UTF-8"),"iso-8859-1");

            response.setHeader("Content-disposition","attachment;filename="+newfilename+".xlsx");
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //导出查询文件
    @RequestMapping(value = "getqueryexcel")
    public void deriveQueryExcel(HttpServletResponse response,int pageNo,String username) {
        List<UserModel> list = queryService.queryLikeUsername(username,(pageNo-1)*maxPage,maxPage);
        XSSFWorkbook wb = createExcel.createExcel(pageNo,list);
        try {
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            String filename =UUID.randomUUID().toString();

//            解决中文名称乱码
            String newfilename = new String(filename.getBytes("UTF-8"),"iso-8859-1");

            response.setHeader("Content-disposition","attachment;filename="+newfilename+".xlsx");
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
