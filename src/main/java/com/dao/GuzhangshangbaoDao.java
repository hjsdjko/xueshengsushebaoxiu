package com.dao;

import com.entity.GuzhangshangbaoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.GuzhangshangbaoView;

/**
 * 故障上报 Dao 接口
 *
 * @author 
 */
public interface GuzhangshangbaoDao extends BaseMapper<GuzhangshangbaoEntity> {

   List<GuzhangshangbaoView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
