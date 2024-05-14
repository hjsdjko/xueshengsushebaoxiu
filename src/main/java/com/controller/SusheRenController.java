
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 宿舍人员
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/susheRen")
public class SusheRenController {
    private static final Logger logger = LoggerFactory.getLogger(SusheRenController.class);

    @Autowired
    private SusheRenService susheRenService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private SusheService susheService;
    @Autowired
    private XueshengService xueshengService;

    @Autowired
    private WeixiuyuanService weixiuyuanService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("维修人员".equals(role))
            params.put("weixiuyuanId",request.getSession().getAttribute("userId"));
        else if("学生".equals(role))
            params.put("xueshengId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = susheRenService.queryPage(params);

        //字典表数据转换
        List<SusheRenView> list =(List<SusheRenView>)page.getList();
        for(SusheRenView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SusheRenEntity susheRen = susheRenService.selectById(id);
        if(susheRen !=null){
            //entity转view
            SusheRenView view = new SusheRenView();
            BeanUtils.copyProperties( susheRen , view );//把实体数据重构到view中

                //级联表
                SusheEntity sushe = susheService.selectById(susheRen.getSusheId());
                if(sushe != null){
                    BeanUtils.copyProperties( sushe , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setSusheId(sushe.getId());
                }
                //级联表
                XueshengEntity xuesheng = xueshengService.selectById(susheRen.getXueshengId());
                if(xuesheng != null){
                    BeanUtils.copyProperties( xuesheng , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setXueshengId(xuesheng.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody SusheRenEntity susheRen, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,susheRen:{}",this.getClass().getName(),susheRen.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            susheRen.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<SusheRenEntity> queryWrapper = new EntityWrapper<SusheRenEntity>()
            .eq("xuesheng_id", susheRen.getXueshengId())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SusheRenEntity susheRenEntity = susheRenService.selectOne(queryWrapper);
        if(susheRenEntity==null){
            susheRen.setInsertTime(new Date());
            susheRen.setCreateTime(new Date());


            List<SusheRenEntity> susheRenEntities = susheRenService.selectList(new EntityWrapper<SusheRenEntity>(susheRenEntity)
                    .eq("sushe_id", susheRen.getSusheId())
            );

            SusheEntity susheEntity = susheService.selectById(susheRen.getSusheId());
            if((susheEntity.getJuzhuNumber()-susheRenEntities.size())<=0)
                return R.error("当前宿舍已住满");

            susheRenService.insert(susheRen);
            return R.ok();
        }else {
            return R.error(511,"该学生已有宿舍");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody SusheRenEntity susheRen, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,susheRen:{}",this.getClass().getName(),susheRen.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("学生".equals(role))
//            susheRen.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<SusheRenEntity> queryWrapper = new EntityWrapper<SusheRenEntity>()
            .notIn("id",susheRen.getId())
            .andNew()
            .eq("xuesheng_id", susheRen.getXueshengId())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SusheRenEntity susheRenEntity = susheRenService.selectOne(queryWrapper);
        if(susheRenEntity==null){
            susheRenService.updateById(susheRen);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"该学生已有宿舍");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        susheRenService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<SusheRenEntity> susheRenList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("../../upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            SusheRenEntity susheRenEntity = new SusheRenEntity();
//                            susheRenEntity.setSusheId(Integer.valueOf(data.get(0)));   //宿舍 要改的
//                            susheRenEntity.setXueshengId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            susheRenEntity.setInsertTime(date);//时间
//                            susheRenEntity.setCreateTime(date);//时间
                            susheRenList.add(susheRenEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        susheRenService.insertBatch(susheRenList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
