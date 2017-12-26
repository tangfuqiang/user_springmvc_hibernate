package com.qingshixun.project.util;

import com.qingshixun.project.model.UserModel;
import com.qingshixun.project.service.LoginService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateExcel {
    @Autowired
    private LoginService loginService;
    private int maxPage = 5;
    public XSSFWorkbook createExcel(int pageNo,List<UserModel> list){

        String table[]={"id","账号","姓名","性别","邮箱","职业","出生日期","创建时间"};

        //创建一个工作薄
        XSSFWorkbook wb = new XSSFWorkbook();

        //创建一个工作表
        XSSFSheet sheet = wb.createSheet("信息表");
        XSSFRow row = sheet.createRow((int) 0);
        XSSFCellStyle style = wb.createCellStyle();
        // 居中格式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        XSSFCell cell = row.createCell(0);
        for (int i = 0; i < table.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(table[i]);
            cell.setCellStyle(style);
        }
        for(int i=0;i<list.size();i++){

            UserModel userModel =(UserModel) list.get(i);
            row=sheet.createRow(i+1);

            cell = row.createCell(0);
            cell.setCellValue(userModel.getId());
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(userModel.getAccount());
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(userModel.getUsername());
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(String.valueOf(userModel.getSex()));
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue(userModel.getEmail());
            cell.setCellStyle(style);

            cell = row.createCell(5);
            cell.setCellValue(userModel.getOccupation());
            cell.setCellStyle(style);

            cell = row.createCell(6);
            cell.setCellValue(String.valueOf(userModel.getBirthday()));
            cell.setCellStyle(style);

            cell = row.createCell(7);
            cell.setCellValue(String.valueOf(userModel.getCreatetime()));
            cell.setCellStyle(style);
        }
        return wb;
    }
}
