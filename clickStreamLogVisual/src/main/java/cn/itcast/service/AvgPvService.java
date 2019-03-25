package cn.itcast.service;


import cn.itcast.pojo.TAvgpvNum;

import java.util.List;

public interface AvgPvService {
    
    public String  getAvgPvNumByDates(String startDate, String endDate);

    List<TAvgpvNum> findAll();
}
