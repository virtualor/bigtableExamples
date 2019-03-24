package cn.itcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.itcast.mapper.TFlowNumMapper;
import cn.itcast.pojo.FlowToPageBean;
import cn.itcast.pojo.TFlowNum;
import cn.itcast.pojo.TFlowNumExample;
import cn.itcast.pojo.TFlowNumExample.Criteria;
import cn.itcast.service.FlowService;

@Service
public class FlowServiceImpl implements FlowService{
    
    @Autowired
    private TFlowNumMapper mapper;

    @Override
    public String getFlowByDates(String startDate, String endDate) {
        
        TFlowNumExample example = new TFlowNumExample();
        Criteria criteria = example.createCriteria();
        criteria.andDatestrBetween(startDate, endDate);
        example.setOrderByClause("`dateStr` ASC");
        List<TFlowNum> lists = mapper.selectByExample(example);
        
        // 数组大小
        int size = 7;
        
        String[] dates = new String[size];
        Integer[] uvs = new Integer[size];
        Integer[] new_uvs = new Integer[size];
        
        int i = 0;
        for (TFlowNum flowNum : lists) {
                dates[i] = flowNum.getDatestr();
                uvs[i] = flowNum.getUvnum();
                new_uvs[i] =flowNum.getNewuvnum();
                i++;
        }
        
        FlowToPageBean bean = new FlowToPageBean();
        bean.setDates(dates);
        bean.setUvs(uvs);
        bean.setNew_uvs(new_uvs);
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
