package com.qingshixun.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.qingshixun.project.model.UserModel;
import com.qingshixun.project.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("user")
public class QueryUserController {
    @Autowired
    private QueryService queryService;

    private boolean check;

    private int startPage = 1;
    private int maxPage = 5;

    //模糊查询结果判断
    @ResponseBody
    @RequestMapping(value = "queryuser")
    public String queryUsername(@RequestParam String username) {
        List<UserModel> userlist = queryService.queryLikeUsername(username, 0, 1);
        if (userlist.size()==0) {
            check = false;
        } else {
            check = true;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("check", check);
        return jsonObject.toJSONString();
    }

    //显示模糊查询结果
    @RequestMapping(value = "toquerpage")
    public ModelAndView toQueryPage(ModelAndView modelAndView,@RequestParam String username, HttpSession session) {

        List<UserModel> userlist = queryService.queryLikeUsername(username, (startPage - 1) * maxPage, maxPage);
        long totalPage = queryService.getQueryTotalPage(maxPage,username);
        session.setAttribute("querytotalPage",totalPage);
        modelAndView.addObject("userlist",userlist);
        modelAndView.addObject("startPage",startPage);
        modelAndView.addObject("username",username);
        modelAndView.setViewName("user/queryUser");
        return modelAndView;
    }
}
