
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
 * 维修人员
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/weixiuyuan")
public class WeixiuyuanController {
    private static final Logger logger = LoggerFactory.getLogger(WeixiuyuanController.class);

    @Autowired
    private WeixiuyuanService weixiuyuanService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

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
        params.put("weixiuyuanDeleteStart",1);params.put("weixiuyuanDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = weixiuyuanService.queryPage(params);

        //字典表数据转换
        List<WeixiuyuanView> list =(List<WeixiuyuanView>)page.getList();
        for(WeixiuyuanView c:list){
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
        WeixiuyuanEntity weixiuyuan = weixiuyuanService.selectById(id);
        if(weixiuyuan !=null){
            //entity转view
            WeixiuyuanView view = new WeixiuyuanView();
            BeanUtils.copyProperties( weixiuyuan , view );//把实体数据重构到view中

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
    public R save(@RequestBody WeixiuyuanEntity weixiuyuan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,weixiuyuan:{}",this.getClass().getName(),weixiuyuan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<WeixiuyuanEntity> queryWrapper = new EntityWrapper<WeixiuyuanEntity>()
            .eq("username", weixiuyuan.getUsername())
            .or()
            .eq("weixiuyuan_phone", weixiuyuan.getWeixiuyuanPhone())
            .andNew()
            .eq("weixiuyuan_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WeixiuyuanEntity weixiuyuanEntity = weixiuyuanService.selectOne(queryWrapper);
        if(weixiuyuanEntity==null){
            weixiuyuan.setWeixiuyuanDelete(1);
            weixiuyuan.setCreateTime(new Date());
            weixiuyuan.setPassword("123456");
            weixiuyuanService.insert(weixiuyuan);
            return R.ok();
        }else {
            return R.error(511,"账户或者联系方式已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody WeixiuyuanEntity weixiuyuan, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,weixiuyuan:{}",this.getClass().getName(),weixiuyuan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<WeixiuyuanEntity> queryWrapper = new EntityWrapper<WeixiuyuanEntity>()
            .notIn("id",weixiuyuan.getId())
            .andNew()
            .eq("username", weixiuyuan.getUsername())
            .or()
            .eq("weixiuyuan_phone", weixiuyuan.getWeixiuyuanPhone())
            .andNew()
            .eq("weixiuyuan_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WeixiuyuanEntity weixiuyuanEntity = weixiuyuanService.selectOne(queryWrapper);
        if("".equals(weixiuyuan.getWeixiuyuanPhoto()) || "null".equals(weixiuyuan.getWeixiuyuanPhoto())){
                weixiuyuan.setWeixiuyuanPhoto(null);
        }
        if(weixiuyuanEntity==null){
            weixiuyuanService.updateById(weixiuyuan);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者联系方式已经被使用");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<WeixiuyuanEntity> list = new ArrayList<>();
        for(Integer id:ids){
            WeixiuyuanEntity weixiuyuanEntity = new WeixiuyuanEntity();
            weixiuyuanEntity.setId(id);
            weixiuyuanEntity.setWeixiuyuanDelete(2);
            list.add(weixiuyuanEntity);
        }
        if(list != null && list.size() >0){
            weixiuyuanService.updateBatchById(list);
        }
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
            List<WeixiuyuanEntity> weixiuyuanList = new ArrayList<>();//上传的东西
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
                            WeixiuyuanEntity weixiuyuanEntity = new WeixiuyuanEntity();
//                            weixiuyuanEntity.setUsername(data.get(0));                    //账号 要改的
//                            //weixiuyuanEntity.setPassword("123456");//密码
//                            weixiuyuanEntity.setWeixiuyuanUuidNumber(data.get(0));                    //工号 要改的
//                            weixiuyuanEntity.setWeixiuyuanName(data.get(0));                    //维修员姓名 要改的
//                            weixiuyuanEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            weixiuyuanEntity.setWeixiuyuanNianxian(data.get(0));                    //工作年限 要改的
//                            weixiuyuanEntity.setWeixiuyuanPhone(data.get(0));                    //联系方式 要改的
//                            weixiuyuanEntity.setWeixiuyuanPhoto("");//详情和图片
//                            weixiuyuanEntity.setWeixiuyuanContent("");//详情和图片
//                            weixiuyuanEntity.setWeixiuyuanDelete(1);//逻辑删除字段
//                            weixiuyuanEntity.setCreateTime(date);//时间
                            weixiuyuanList.add(weixiuyuanEntity);


                            //把要查询是否重复的字段放入map中
                                //账号
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //工号
                                if(seachFields.containsKey("weixiuyuanUuidNumber")){
                                    List<String> weixiuyuanUuidNumber = seachFields.get("weixiuyuanUuidNumber");
                                    weixiuyuanUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> weixiuyuanUuidNumber = new ArrayList<>();
                                    weixiuyuanUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("weixiuyuanUuidNumber",weixiuyuanUuidNumber);
                                }
                                //联系方式
                                if(seachFields.containsKey("weixiuyuanPhone")){
                                    List<String> weixiuyuanPhone = seachFields.get("weixiuyuanPhone");
                                    weixiuyuanPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> weixiuyuanPhone = new ArrayList<>();
                                    weixiuyuanPhone.add(data.get(0));//要改的
                                    seachFields.put("weixiuyuanPhone",weixiuyuanPhone);
                                }
                        }

                        //查询是否重复
                         //账号
                        List<WeixiuyuanEntity> weixiuyuanEntities_username = weixiuyuanService.selectList(new EntityWrapper<WeixiuyuanEntity>().in("username", seachFields.get("username")).eq("weixiuyuan_delete", 1));
                        if(weixiuyuanEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(WeixiuyuanEntity s:weixiuyuanEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //工号
                        List<WeixiuyuanEntity> weixiuyuanEntities_weixiuyuanUuidNumber = weixiuyuanService.selectList(new EntityWrapper<WeixiuyuanEntity>().in("weixiuyuan_uuid_number", seachFields.get("weixiuyuanUuidNumber")).eq("weixiuyuan_delete", 1));
                        if(weixiuyuanEntities_weixiuyuanUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(WeixiuyuanEntity s:weixiuyuanEntities_weixiuyuanUuidNumber){
                                repeatFields.add(s.getWeixiuyuanUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [工号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //联系方式
                        List<WeixiuyuanEntity> weixiuyuanEntities_weixiuyuanPhone = weixiuyuanService.selectList(new EntityWrapper<WeixiuyuanEntity>().in("weixiuyuan_phone", seachFields.get("weixiuyuanPhone")).eq("weixiuyuan_delete", 1));
                        if(weixiuyuanEntities_weixiuyuanPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(WeixiuyuanEntity s:weixiuyuanEntities_weixiuyuanPhone){
                                repeatFields.add(s.getWeixiuyuanPhone());
                            }
                            return R.error(511,"数据库的该表中的 [联系方式] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        weixiuyuanService.insertBatch(weixiuyuanList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }


    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        WeixiuyuanEntity weixiuyuan = weixiuyuanService.selectOne(new EntityWrapper<WeixiuyuanEntity>().eq("username", username));
        if(weixiuyuan==null || !weixiuyuan.getPassword().equals(password))
            return R.error("账号或密码不正确");
        else if(weixiuyuan.getWeixiuyuanDelete() != 1)
            return R.error("账户已被删除");
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(.getRoleTypes());
        String token = tokenService.generateToken(weixiuyuan.getId(),username, "weixiuyuan", "维修人员");
        R r = R.ok();
        r.put("token", token);
        r.put("role","维修人员");
        r.put("username",weixiuyuan.getWeixiuyuanName());
        r.put("tableName","weixiuyuan");
        r.put("userId",weixiuyuan.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody WeixiuyuanEntity weixiuyuan){
//    	ValidatorUtils.validateEntity(user);
        Wrapper<WeixiuyuanEntity> queryWrapper = new EntityWrapper<WeixiuyuanEntity>()
            .eq("username", weixiuyuan.getUsername())
            .or()
            .eq("weixiuyuan_phone", weixiuyuan.getWeixiuyuanPhone())
            .andNew()
            .eq("weixiuyuan_delete", 1)
            ;
        WeixiuyuanEntity weixiuyuanEntity = weixiuyuanService.selectOne(queryWrapper);
        if(weixiuyuanEntity != null)
            return R.error("账户或者联系方式已经被使用");
        weixiuyuan.setWeixiuyuanDelete(1);
        weixiuyuan.setCreateTime(new Date());
        weixiuyuanService.insert(weixiuyuan);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        WeixiuyuanEntity weixiuyuan = new WeixiuyuanEntity();
        weixiuyuan.setPassword("123456");
        weixiuyuan.setId(id);
        weixiuyuanService.updateById(weixiuyuan);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        WeixiuyuanEntity weixiuyuan = weixiuyuanService.selectOne(new EntityWrapper<WeixiuyuanEntity>().eq("username", username));
        if(weixiuyuan!=null){
            weixiuyuan.setPassword("123456");
            boolean b = weixiuyuanService.updateById(weixiuyuan);
            if(!b){
               return R.error();
            }
        }else{
           return R.error("账号不存在");
        }
        return R.ok();
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrWeixiuyuan(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        WeixiuyuanEntity weixiuyuan = weixiuyuanService.selectById(id);
        if(weixiuyuan !=null){
            //entity转view
            WeixiuyuanView view = new WeixiuyuanView();
            BeanUtils.copyProperties( weixiuyuan , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }





}
