package com.entity.model;

import com.entity.GuzhangshangbaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 故障上报
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class GuzhangshangbaoModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 学生
     */
    private Integer xueshengId;


    /**
     * 维修人员
     */
    private Integer weixiuyuanId;


    /**
     * 上报编号
     */
    private String guzhangshangbaoUuidNumber;


    /**
     * 物品分类
     */
    private Integer wupinTypes;


    /**
     * 故障分类
     */
    private Integer guzhangshangbaoTypes;


    /**
     * 报修位置
     */
    private String guzhangshangbaoAddress;


    /**
     * 上报时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 上报详情
     */
    private String forumContent;


    /**
     * 维修状态
     */
    private Integer weixiuzhuangtaiTypes;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：学生
	 */
    public Integer getXueshengId() {
        return xueshengId;
    }


    /**
	 * 设置：学生
	 */
    public void setXueshengId(Integer xueshengId) {
        this.xueshengId = xueshengId;
    }
    /**
	 * 获取：维修人员
	 */
    public Integer getWeixiuyuanId() {
        return weixiuyuanId;
    }


    /**
	 * 设置：维修人员
	 */
    public void setWeixiuyuanId(Integer weixiuyuanId) {
        this.weixiuyuanId = weixiuyuanId;
    }
    /**
	 * 获取：上报编号
	 */
    public String getGuzhangshangbaoUuidNumber() {
        return guzhangshangbaoUuidNumber;
    }


    /**
	 * 设置：上报编号
	 */
    public void setGuzhangshangbaoUuidNumber(String guzhangshangbaoUuidNumber) {
        this.guzhangshangbaoUuidNumber = guzhangshangbaoUuidNumber;
    }
    /**
	 * 获取：物品分类
	 */
    public Integer getWupinTypes() {
        return wupinTypes;
    }


    /**
	 * 设置：物品分类
	 */
    public void setWupinTypes(Integer wupinTypes) {
        this.wupinTypes = wupinTypes;
    }
    /**
	 * 获取：故障分类
	 */
    public Integer getGuzhangshangbaoTypes() {
        return guzhangshangbaoTypes;
    }


    /**
	 * 设置：故障分类
	 */
    public void setGuzhangshangbaoTypes(Integer guzhangshangbaoTypes) {
        this.guzhangshangbaoTypes = guzhangshangbaoTypes;
    }
    /**
	 * 获取：报修位置
	 */
    public String getGuzhangshangbaoAddress() {
        return guzhangshangbaoAddress;
    }


    /**
	 * 设置：报修位置
	 */
    public void setGuzhangshangbaoAddress(String guzhangshangbaoAddress) {
        this.guzhangshangbaoAddress = guzhangshangbaoAddress;
    }
    /**
	 * 获取：上报时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：上报时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：上报详情
	 */
    public String getForumContent() {
        return forumContent;
    }


    /**
	 * 设置：上报详情
	 */
    public void setForumContent(String forumContent) {
        this.forumContent = forumContent;
    }
    /**
	 * 获取：维修状态
	 */
    public Integer getWeixiuzhuangtaiTypes() {
        return weixiuzhuangtaiTypes;
    }


    /**
	 * 设置：维修状态
	 */
    public void setWeixiuzhuangtaiTypes(Integer weixiuzhuangtaiTypes) {
        this.weixiuzhuangtaiTypes = weixiuzhuangtaiTypes;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
