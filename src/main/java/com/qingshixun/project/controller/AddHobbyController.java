package com.qingshixun.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.qingshixun.project.service.AddHobbyService;
import com.qingshixun.project.service.QueryService;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("hobby")
public class AddHobbyController {

    @Autowired
    private AddHobbyService addHobbyService;

    @Autowired
    private QueryService queryService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public ModelAndView toAddHobbyPage(ModelAndView modelAndView){
        modelAndView.setViewName("hobby/addhobby");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "addhobby",method = RequestMethod.POST)
    public String addHobby(String hobby){
        System.out.println(hobby);
        boolean mesage = false;
        if(queryService.queryHobby(hobby)==null){
            mesage = true;
            addHobbyService.addHobby(hobby);
        }
        JSONObject jsonObject= new JSONObject();
        jsonObject.put("mesage",mesage);
        return jsonObject.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "queryhobby",method = RequestMethod.POST)
    public String queryHobby(String hobby){
        boolean mesage = false;
        if(queryService.queryHobby(hobby)==null){
            mesage = true;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mesage",mesage);
        return jsonObject.toJSONString();
    }
}
