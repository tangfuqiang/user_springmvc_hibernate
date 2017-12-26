package com.qingshixun.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.qingshixun.project.model.HobbyModel;
import com.qingshixun.project.model.OccupationModel;
import com.qingshixun.project.model.UserModel;
import com.qingshixun.project.service.AddUserService;
import com.qingshixun.project.service.QueryService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("add")
public class AddUserController {

    @Autowired
    private QueryService queryService;
    @Autowired
    private AddUserService addUserService;

    //添加用户
    @ResponseBody
    @RequestMapping(value = "adduser")
    public String addUser(UserModel userModel, String birth) throws ParseException {
        boolean check = true;
        System.out.println(userModel.toString());
        if (queryService.queryUsername(userModel.getUsername()) != null) {
            check = false;
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            userModel.setBirthday(simpleDateFormat.parse(birth));
            userModel.setCreatetime(new Date());
            addUserService.addUser(userModel);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("check", check);
        System.out.println(userModel.toString());
        return jsonObject.toJSONString();
    }

    //跳转到用户添加页面
    @RequestMapping(value = "toaddpage", method = RequestMethod.GET)
    public ModelAndView toAddPage(ModelAndView modelAndView) {

        List<OccupationModel> occupatiolist = queryService.queryAllOccupation();
        List<HobbyModel> hobbyList = queryService.queryAllHobby();
        modelAndView.addObject("occupatiolist", occupatiolist);
        modelAndView.addObject("hobbyList", hobbyList);
        modelAndView.setViewName("user/UserForm");
        return modelAndView;
    }

    //上传头像
    @ResponseBody
    @RequestMapping(value = "uploadimg", method = RequestMethod.POST)
    public String uploadImag(MultipartFile file, HttpServletRequest request) {
        boolean mess = false;
        // 解决文件名冲突
        String fileName = file.getOriginalFilename();
        String newFileName = UUID.randomUUID().toString() + fileName.substring(fileName.indexOf("."), fileName.length());
        String url = request.getServletContext().getRealPath("/userimg");
        System.out.println(url);
        if (!new File(url).exists()) {
            new File(url).mkdirs();
        }
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(url + File.separator + newFileName));
            mess = true;
        } catch (IOException e) {
            e.printStackTrace();
            mess = false;
        }


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mess", mess);
        jsonObject.put("url", newFileName);
        return jsonObject.toJSONString();

    }
}
