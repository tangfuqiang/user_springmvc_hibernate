package com.qingshixun.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.qingshixun.project.model.UserModel;
import com.qingshixun.project.service.DeleteUserService;
import com.qingshixun.project.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
@RequestMapping("user")
public class RemoveQueryController {

    @Autowired
    private DeleteUserService deleteUserService;

    @Autowired
    private QueryService queryService;

    private long total;

    private int maxPage=5;

    //删除查询出的结果某条数据
    @ResponseBody
    @RequestMapping(value = "removequryuser")
    public String removeQueryUser(String username,long id,int pageNo,HttpSession session){
        List<UserModel> newuserlist;
        deleteUserService.removeUser(id);
        total = queryService.getQueryTotalPage(maxPage,username);
        session.setAttribute("querytotalPage",total);
        if(pageNo>total){
            pageNo--;
            newuserlist =  queryService.queryLikeUsername(username,(pageNo-1)*maxPage,maxPage);
        }else {
            newuserlist =  queryService.queryLikeUsername(username,(pageNo-1)*maxPage,maxPage);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("newuserlist",newuserlist);
        jsonObject.put("totalPage",total);
        jsonObject.put("startPage",pageNo);
        return jsonObject.toJSONString();
    }

    //删除查询出结果选中的数据
    @ResponseBody
    @RequestMapping(value = "removequerysome")
    public String removeQuerySomeUser(@RequestParam(value = "ids[]") long ids[], String username, int pageNo, HttpSession session){
        List<UserModel> newuserlist;
        deleteUserService.removeSomeUser(ids);
        total = queryService.getQueryTotalPage(maxPage,username);
        session.setAttribute("querytotalPage",total);
        if(pageNo>total){
            pageNo--;
            newuserlist =  queryService.queryLikeUsername(username,(pageNo-1)*maxPage,maxPage);
        }else {
            newuserlist =  queryService.queryLikeUsername(username,(pageNo-1)*maxPage,maxPage);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("newuserlist",newuserlist);
        jsonObject.put("totalPage",total);
        jsonObject.put("startPage",pageNo);
        return jsonObject.toJSONString();
    }
}
