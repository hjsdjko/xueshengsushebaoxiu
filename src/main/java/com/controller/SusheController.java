
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
 * 宿舍
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/sushe")
public class SusheController {
    private static final Logger logger = LoggerFactory.getLogger(SusheController.class);

    @Autowired
    private SusheService susheService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private WeixiuyuanService weixiuyuanService;
    @Autowired
    private XueshengService xueshengService;
    @Autowired
    private SusheRenService susheRenService;


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
        else if("学生".equals(role)){
            SusheRenEntity susheRenEntity = susheRenService.selectOne(
                new EntityWrapper<SusheRenEntity>()
                    .eq("xuesheng_id", request.getSession().getAttribute("userId"))
            );
            if(susheRenEntity == null)
                params.put("susheId",-1);
            else
                params.put("susheId",susheRenEntity.getSusheId());
//            params.put("xueshengId",request.getSession().getAttribute("userId"));
        }
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = susheService.queryPage(params);

        //字典表数据转换
        List<SusheView> list =(List<SusheView>)page.getList();
        for(SusheView c:list){
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
        SusheEntity sushe = susheService.selectById(id);
        if(sushe !=null){
            //entity转view
            SusheView view = new SusheView();
            BeanUtils.copyProperties( sushe , view );//把实体数据重构到view中

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
    public R save(@RequestBody SusheEntity sushe, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,sushe:{}",this.getClass().getName(),sushe.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<SusheEntity> queryWrapper = new EntityWrapper<SusheEntity>()
            .eq("loudong_types", sushe.getLoudongTypes())
            .eq("danyuan_types", sushe.getDanyuanTypes())
            .eq("sushe_uuid_number", sushe.getSusheUuidNumber())
            .eq("sushe_address", sushe.getSusheAddress())
            .eq("juzhu_number", sushe.getJuzhuNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SusheEntity susheEntity = susheService.selectOne(queryWrapper);
        if(susheEntity==null){
            sushe.setInsertTime(new Date());
            sushe.setCreateTime(new Date());
            susheService.insert(sushe);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody SusheEntity sushe, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,sushe:{}",this.getClass().getName(),sushe.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<SusheEntity> queryWrapper = new EntityWrapper<SusheEntity>()
            .notIn("id",sushe.getId())
            .andNew()
            .eq("loudong_types", sushe.getLoudongTypes())
            .eq("danyuan_types", sushe.getDanyuanTypes())
            .eq("sushe_uuid_number", sushe.getSusheUuidNumber())
            .eq("sushe_address", sushe.getSusheAddress())
            .eq("juzhu_number", sushe.getJuzhuNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SusheEntity susheEntity = susheService.selectOne(queryWrapper);
        if(susheEntity==null){
            susheService.updateById(sushe);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        susheService.deleteBatchIds(Arrays.asList(ids));
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
            List<SusheEntity> susheList = new ArrayList<>();//上传的东西
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
                            SusheEntity susheEntity = new SusheEntity();
//                            susheEntity.setLoudongTypes(Integer.valueOf(data.get(0)));   //楼栋 要改的
//                            susheEntity.setDanyuanTypes(Integer.valueOf(data.get(0)));   //单元 要改的
//                            susheEntity.setSusheUuidNumber(data.get(0));                    //宿舍号 要改的
//                            susheEntity.setSusheAddress(data.get(0));                    //宿舍位置 要改的
//                            susheEntity.setJuzhuNumber(Integer.valueOf(data.get(0)));   //最大居住人数 要改的
//                            susheEntity.setSusheContent("");//详情和图片
//                            susheEntity.setInsertTime(date);//时间
//                            susheEntity.setCreateTime(date);//时间
                            susheList.add(susheEntity);


                            //把要查询是否重复的字段放入map中
                                //宿舍号
                                if(seachFields.containsKey("susheUuidNumber")){
                                    List<String> susheUuidNumber = seachFields.get("susheUuidNumber");
                                    susheUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> susheUuidNumber = new ArrayList<>();
                                    susheUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("susheUuidNumber",susheUuidNumber);
                                }
                        }

                        //查询是否重复
                         //宿舍号
                        List<SusheEntity> susheEntities_susheUuidNumber = susheService.selectList(new EntityWrapper<SusheEntity>().in("sushe_uuid_number", seachFields.get("susheUuidNumber")));
                        if(susheEntities_susheUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(SusheEntity s:susheEntities_susheUuidNumber){
                                repeatFields.add(s.getSusheUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [宿舍号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        susheService.insertBatch(susheList);
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
