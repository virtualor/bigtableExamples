package cn.itcast.service.impl;

import java.util.Iterator;
import java.util.List;

import com.sun.glass.ui.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.itcast.mapper.TAvgpvNumMapper;
import cn.itcast.pojo.AvgToPageBean;
import cn.itcast.pojo.TAvgpvNum;
import cn.itcast.pojo.TAvgpvNumExample;
import cn.itcast.pojo.TAvgpvNumExample.Criteria;
import cn.itcast.service.AvgPvService;




@Service
public class AvgPvServiceImpl implements AvgPvService{
    
    @Autowired
    private TAvgpvNumMapper mapper;


    @Override
    public String getAvgPvNumByDates(String startDate, String endDate) {

/*        TAvgpvNumExample example  = new TAvgpvNumExample();
        Criteria criteria = example.createCriteria();
        criteria.andDatestrBetween(startDate,endDate);
        example.setOrderByClause("'dateStr asc");

        List<TAvgpvNum> tAvgpvNums = mapper.selectByExample(example);

        int size =7;
        String[] dates = new String[size];
        double[] data = new double[size];

        int i = 0;
        for (TAvgpvNum tAvgpvNum : tAvgpvNums) {
            dates[i] = tAvgpvNum.getDatestr();
            data[i] = tAvgpvNum.getAvgpvnum().doubleValue();
            i++;
        }

        AvgToPageBean bean = new AvgToPageBean();
        bean.setData(data);
        bean.setDates(dates);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;

    }*/
        TAvgpvNumExample example = new TAvgpvNumExample();
        
        Criteria criteria = example.createCriteria();
        
        criteria.andDatestrBetween(startDate, endDate);
        
        example.setOrderByClause("`dateStr` ASC");
        List<TAvgpvNum> lists = mapper.selectByExample(example);
        // 数组大小
        int size = 7;
        
        String[] dates = new String[size];
        double[] datas = new double[size];
        
        int i = 0;
        for (TAvgpvNum tAvgpvNum : lists) {
                dates[i]=tAvgpvNum.getDatestr();
                datas[i]=tAvgpvNum.getAvgpvnum().doubleValue();
                i++;
        }
        
        AvgToPageBean bean = new AvgToPageBean();
        bean.setDates(dates);
        bean.setData(datas);
        ObjectMapper om = new ObjectMapper();
        String beanJson= null;
        try {
            beanJson = om.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return beanJson;
    }


}
