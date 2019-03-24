package cn.itcast.controller;

import cn.itcast.service.AvgPvService;
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
public class AvgPvNumController {
    @Autowired
    private AvgPvService pvSeivice;

    @RequestMapping(value="/avgPvNum",produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getBarChart1() throws Exception{

        System.out.println("获取平均pv数据...");
        String s0 = "hello world";

        String s = pvSeivice.getAvgPvNumByDates("20130919", "20130925");

        return s;

    }
}
