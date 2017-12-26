package com.qingshixun.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.qingshixun.project.model.OccupationModel;
import com.qingshixun.project.service.QueryService;
import com.qingshixun.project.service.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("user")
public class UpdateUserController {

    private boolean check=true;

    @Autowired
    private UpdateUserService updateUserService;

    @Autowired
    private QueryService queryService;

    //修改用户信息
    @ResponseBody
    @RequestMapping(value = "updateuser")
    public String updateUser(@RequestParam  long id,String username,String occupation,String hobby,String email,String sex,String birthday,String usernamecheck) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        JSONObject jsonObject = new JSONObject();
        //判断用户名是否更改
        if(Boolean.valueOf(usernamecheck)){
            if(queryService.queryUsername(username)==null){
                updateUserService.updateUser(id, username, occupation, hobby, email, sex.charAt(0),simpleDateFormat.parse(birthday));
                check = true;
            }else {
                check=false;
            }
        }else {
            updateUserService.updateUser(id, username, occupation, hobby, email, sex.charAt(0), simpleDateFormat.parse(birthday));
        }
        jsonObject.put("check",check);
        return jsonObject.toJSONString();
    }

    //获取所有职位
    @ResponseBody
    @RequestMapping(value = "getnews")
    public String getNews(){

        List<OccupationModel> list = queryService.queryAllOccupation();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("oclist",list);
        return jsonObject.toJSONString();
    }
}
