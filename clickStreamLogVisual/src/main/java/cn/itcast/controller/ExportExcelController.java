package cn.itcast.controller;

import cn.itcast.pojo.TAvgpvNum;
import cn.itcast.service.AvgPvService;
import cn.itcast.utils.ExportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Date:2019/3/26
 * Author:jianxian mao
 * Describe:
 */
@RequestMapping("/exportExcel")
@RestController
public class ExportExcelController {

    @Autowired
    private AvgPvService pvSeivice;

    @Autowired
    private HttpServletResponse response;


    @RequestMapping("/avgPvNum")
    public void avgPvNum() throws Exception{

        response.setContentType("application/x-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" +
                new String("近7日平均访问量.xls".getBytes(), "ISO-8859-1"));

        ServletOutputStream outputStream = response.getOutputStream();

        List<TAvgpvNum> list = pvSeivice.findAll();

        ExportExcelUtil.exportAvgPvNum(list, outputStream);
        if (outputStream != null) {
            outputStream.close();
        }



    }
}
