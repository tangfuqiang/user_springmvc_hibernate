package com.qingshixun.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.qingshixun.project.model.OccupationModel;
import com.qingshixun.project.service.AddOccupationService;
import com.qingshixun.project.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("occupation")
public class AddOccupationController {

    @Autowired
    private AddOccupationService addOccupationService;

    @Autowired
    private QueryService queryService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView toOccupationPage(ModelAndView modelAndView) {
        modelAndView.setViewName("profession/addoccupation");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "addoccupation", method = RequestMethod.POST)
    public String addOccupation(String occupation) {
        boolean mesage = false;
        if (queryService.queryOccupation(occupation) == null) {
            OccupationModel occupationModel = new OccupationModel();
            occupationModel.setOccupation(occupation);
            occupationModel.setCreatetime(new Date());
            addOccupationService.addOccupation(occupationModel);
            mesage = true;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mesage", mesage);
        return jsonObject.toJSONString();
    }
}
