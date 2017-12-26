package com.qingshixun.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.qingshixun.project.model.UserModel;
import com.qingshixun.project.service.DeleteUserService;
import com.qingshixun.project.service.LoginService;
import com.qingshixun.project.service.TotalPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("user")
public class DeleteUserController {

    @Autowired
    private DeleteUserService deleteUserService;

    @Autowired
    private TotalPageService totalPageService;

    @Autowired
    private LoginService loginService;

    private int maxPage = 5;

    private List<UserModel> newuserlist;

    //删除用户
    @ResponseBody
    @RequestMapping(value = "removeUser")
    public String removeUser(@RequestParam int pageNo, long id, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        deleteUserService.removeUser(id);
        int totalPage = totalPageService.getTotalPage(maxPage);
        session.setAttribute("totalPage", totalPage);
        //判断总页数是否改变
        if (pageNo > totalPage) {
            pageNo--;
            newuserlist = loginService.loginPass((pageNo - 2) * maxPage, maxPage);
        } else {
            newuserlist = loginService.loginPass((pageNo - 1) * maxPage, maxPage);
        }
        jsonObject.put("jsontotal",totalPage);
        jsonObject.put("startPage", pageNo);
        jsonObject.put("newuserlist", newuserlist);
        return jsonObject.toJSONString();
    }

    //批量删除
    @ResponseBody
    @RequestMapping(value = "removesomeuser")
    public String removeSomeUser(@RequestParam(value = "ids[]") long[] ids, int pageNo, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        deleteUserService.removeSomeUser(ids);
        int totalPage = totalPageService.getTotalPage(maxPage);
        session.setAttribute("totalPage", totalPage);
        if (pageNo > totalPage ) {
            pageNo--;
            newuserlist = loginService.loginPass((pageNo - 2) * maxPage, maxPage);
        } else {
            newuserlist = loginService.loginPass((pageNo - 1) * maxPage, maxPage);
        }
        jsonObject.put("jsontotal",totalPage);
        jsonObject.put("startPage", pageNo);
        jsonObject.put("newuserlist", newuserlist);
        return jsonObject.toJSONString();
    }

}
