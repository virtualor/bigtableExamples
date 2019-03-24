package cn.itcast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Date:2019/3/20
 * Author:moriton
 * Desc:
 */
@Controller
public class IndexController {

    @RequestMapping("index")
    public String toIndex(){
        return "index";
    }
}
