package com.service.impl;

import com.utils.StringUtil;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import com.dao.GuzhangshangbaoDao;
import com.entity.GuzhangshangbaoEntity;
import com.service.GuzhangshangbaoService;
import com.entity.view.GuzhangshangbaoView;

/**
 * 故障上报 服务实现类
 */
@Service("guzhangshangbaoService")
@Transactional
public class GuzhangshangbaoServiceImpl extends ServiceImpl<GuzhangshangbaoDao, GuzhangshangbaoEntity> implements GuzhangshangbaoService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<GuzhangshangbaoView> page =new Query<GuzhangshangbaoView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
