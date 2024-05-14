
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
 * 故障上报
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/guzhangshangbao")
public class GuzhangshangbaoController {
    private static final Logger logger = LoggerFactory.getLogger(GuzhangshangbaoController.class);

    @Autowired
    private GuzhangshangbaoService guzhangshangbaoService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private WeixiuyuanService weixiuyuanService;
    @Autowired
    private XueshengService xueshengService;



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
        PageUtils page = guzhangshangbaoService.queryPage(params);

        //字典表数据转换
        List<GuzhangshangbaoView> list =(List<GuzhangshangbaoView>)page.getList();
        for(GuzhangshangbaoView c:list){
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
        GuzhangshangbaoEntity guzhangshangbao = guzhangshangbaoService.selectById(id);
        if(guzhangshangbao !=null){
            //entity转view
            GuzhangshangbaoView view = new GuzhangshangbaoView();
            BeanUtils.copyProperties( guzhangshangbao , view );//把实体数据重构到view中

                //级联表
                WeixiuyuanEntity weixiuyuan = weixiuyuanService.selectById(guzhangshangbao.getWeixiuyuanId());
                if(weixiuyuan != null){
                    BeanUtils.copyProperties( weixiuyuan , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setWeixiuyuanId(weixiuyuan.getId());
                }
                //级联表
                XueshengEntity xuesheng = xueshengService.selectById(guzhangshangbao.getXueshengId());
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
    public R save(@RequestBody GuzhangshangbaoEntity guzhangshangbao, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,guzhangshangbao:{}",this.getClass().getName(),guzhangshangbao.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            guzhangshangbao.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("维修人员".equals(role))
            guzhangshangbao.setWeixiuyuanId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            guzhangshangbao.setInsertTime(new Date());
            guzhangshangbao.setCreateTime(new Date());
            guzhangshangbao.setWeixiuzhuangtaiTypes(1);
            guzhangshangbaoService.insert(guzhangshangbao);
            return R.ok();

    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody GuzhangshangbaoEntity guzhangshangbao, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,guzhangshangbao:{}",this.getClass().getName(),guzhangshangbao.toString());

            guzhangshangbaoService.updateById(guzhangshangbao);//根据id更新
            return R.ok();

    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        guzhangshangbaoService.deleteBatchIds(Arrays.asList(ids));
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
            List<GuzhangshangbaoEntity> guzhangshangbaoList = new ArrayList<>();//上传的东西
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
                            GuzhangshangbaoEntity guzhangshangbaoEntity = new GuzhangshangbaoEntity();
//                            guzhangshangbaoEntity.setXueshengId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            guzhangshangbaoEntity.setWeixiuyuanId(Integer.valueOf(data.get(0)));   //维修人员 要改的
//                            guzhangshangbaoEntity.setGuzhangshangbaoUuidNumber(data.get(0));                    //上报编号 要改的
//                            guzhangshangbaoEntity.setWupinTypes(Integer.valueOf(data.get(0)));   //物品分类 要改的
//                            guzhangshangbaoEntity.setGuzhangshangbaoTypes(Integer.valueOf(data.get(0)));   //故障分类 要改的
//                            guzhangshangbaoEntity.setGuzhangshangbaoAddress(data.get(0));                    //报修位置 要改的
//                            guzhangshangbaoEntity.setInsertTime(date);//时间
//                            guzhangshangbaoEntity.setForumContent("");//详情和图片
//                            guzhangshangbaoEntity.setWeixiuzhuangtaiTypes(Integer.valueOf(data.get(0)));   //维修状态 要改的
//                            guzhangshangbaoEntity.setCreateTime(date);//时间
                            guzhangshangbaoList.add(guzhangshangbaoEntity);


                            //把要查询是否重复的字段放入map中
                                //上报编号
                                if(seachFields.containsKey("guzhangshangbaoUuidNumber")){
                                    List<String> guzhangshangbaoUuidNumber = seachFields.get("guzhangshangbaoUuidNumber");
                                    guzhangshangbaoUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> guzhangshangbaoUuidNumber = new ArrayList<>();
                                    guzhangshangbaoUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("guzhangshangbaoUuidNumber",guzhangshangbaoUuidNumber);
                                }
                        }

                        //查询是否重复
                         //上报编号
                        List<GuzhangshangbaoEntity> guzhangshangbaoEntities_guzhangshangbaoUuidNumber = guzhangshangbaoService.selectList(new EntityWrapper<GuzhangshangbaoEntity>().in("guzhangshangbao_uuid_number", seachFields.get("guzhangshangbaoUuidNumber")));
                        if(guzhangshangbaoEntities_guzhangshangbaoUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(GuzhangshangbaoEntity s:guzhangshangbaoEntities_guzhangshangbaoUuidNumber){
                                repeatFields.add(s.getGuzhangshangbaoUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [上报编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        guzhangshangbaoService.insertBatch(guzhangshangbaoList);
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
