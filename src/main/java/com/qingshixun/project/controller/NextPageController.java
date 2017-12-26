package com.qingshixun.project.controller;

import com.qingshixun.project.model.UserModel;
import com.qingshixun.project.service.LoginService;
import com.qingshixun.project.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("user")
public class NextPageController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private QueryService queryService;

    private int maxpage=5;

    private List<UserModel> userlist;

    //下一页
    @RequestMapping("nextpage")
    public ModelAndView getNextPageUser(int startPage,ModelAndView modelAndView){

        userlist=loginService.loginPass((startPage-1)*maxpage,maxpage);
        modelAndView.addObject("startPage",startPage);
        modelAndView.addObject("userlist",userlist);
        modelAndView.setViewName("user/index");
        return modelAndView;
    }

    //查询结果下一页
    @RequestMapping("nextquerypage")
    public ModelAndView getNextQueryPage(String username,int pageNo,ModelAndView modelAndView){
        System.out.println("username:"+username+",pageNo:"+pageNo);
        userlist = queryService.queryLikeUsername(username,(pageNo-1)*maxpage,maxpage);
        modelAndView.addObject("username",username);
        modelAndView.addObject("startPage",pageNo);
        modelAndView.addObject("userlist",userlist);
        modelAndView.setViewName("user/queryUser");
        return modelAndView;
    }
}
