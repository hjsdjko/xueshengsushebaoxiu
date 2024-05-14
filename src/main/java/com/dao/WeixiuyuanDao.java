package com.dao;

import com.entity.WeixiuyuanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.WeixiuyuanView;

/**
 * 维修人员 Dao 接口
 *
 * @author 
 */
public interface WeixiuyuanDao extends BaseMapper<WeixiuyuanEntity> {

   List<WeixiuyuanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
