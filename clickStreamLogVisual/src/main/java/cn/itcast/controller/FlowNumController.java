package cn.itcast.controller;

import cn.itcast.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Date:2019/3/20
 * Author:moriton
 * Desc:
 */
@Controller
public class FlowNumController {
    @Autowired
    private FlowService flowService;

    @RequestMapping(value = "flowNum",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getFlowByDates(){
        String s  = flowService.getFlowByDates("20131001", "20131007");
        return  s;
    }
}
