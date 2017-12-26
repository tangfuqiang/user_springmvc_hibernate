package com.qingshixun.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.qingshixun.project.model.AdminModel;
import com.qingshixun.project.model.UserModel;
import com.qingshixun.project.service.LoginService;
import com.qingshixun.project.service.TotalPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private TotalPageService totalPageService;

    private boolean check = false;

    private int startpage = 1;

    private int maxpage = 5;

    private int totalPage;

    private List<UserModel> userlist;

    @RequestMapping("login")
    public String toLogin(HttpServletRequest request) {
        //未通过验证SPRING SECURITY 产生
        Object objexception = request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");

        if (objexception != null) {
            Exception exception = (Exception) objexception;
            String mesage = "用户名或密码错误";
            request.setAttribute("mesage", mesage);
        }
        return "user/login";
    }

    //登录信息判断
//    @ResponseBody
//    @RequestMapping(value = "logincheck",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
//    public String loginCheck(String username,String password){
//
//        AdminModel adminModel = loginService.loginCheck(username,password);
//        JSONObject jsonObject = new JSONObject();
//        if(adminModel!=null){
//            check=true;
//            jsonObject.put("check",check);
//            return jsonObject.toJSONString();
//        }else {
//            jsonObject.put("check",check);
//            return jsonObject.toJSONString();
//        }
//    }

    //登录成功，获取用户信息
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView getAllUser(ModelAndView modelAndView, HttpSession session) {

        totalPage = totalPageService.getTotalPage(maxpage);
        userlist = loginService.loginPass((startpage - 1) * maxpage, maxpage);

        session.setAttribute("totalPage", totalPage);
        modelAndView.addObject("userlist", userlist);
        modelAndView.addObject("startPage", startpage);

        modelAndView.setViewName("user/index");
        return modelAndView;
    }


}
