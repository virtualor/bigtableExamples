package cn.itcast.mapper;

import cn.itcast.pojo.TFlowNum;
import cn.itcast.pojo.TFlowNumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TFlowNumMapper {
    int countByExample(TFlowNumExample example);

    int deleteByExample(TFlowNumExample example);

    int insert(TFlowNum record);

    int insertSelective(TFlowNum record);

    List<TFlowNum> selectByExample(TFlowNumExample example);

    int updateByExampleSelective(@Param("record") TFlowNum record, @Param("example") TFlowNumExample example);

    int updateByExample(@Param("record") TFlowNum record, @Param("example") TFlowNumExample example);
}