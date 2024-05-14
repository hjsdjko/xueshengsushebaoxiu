package com.entity.model;

import com.entity.SusheEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 宿舍
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class SusheModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 楼栋
     */
    private Integer loudongTypes;


    /**
     * 单元
     */
    private Integer danyuanTypes;


    /**
     * 宿舍号
     */
    private String susheUuidNumber;


    /**
     * 宿舍位置
     */
    private String susheAddress;


    /**
     * 最大居住人数
     */
    private Integer juzhuNumber;


    /**
     * 宿舍详情
     */
    private String susheContent;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


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
	 * 获取：楼栋
	 */
    public Integer getLoudongTypes() {
        return loudongTypes;
    }


    /**
	 * 设置：楼栋
	 */
    public void setLoudongTypes(Integer loudongTypes) {
        this.loudongTypes = loudongTypes;
    }
    /**
	 * 获取：单元
	 */
    public Integer getDanyuanTypes() {
        return danyuanTypes;
    }


    /**
	 * 设置：单元
	 */
    public void setDanyuanTypes(Integer danyuanTypes) {
        this.danyuanTypes = danyuanTypes;
    }
    /**
	 * 获取：宿舍号
	 */
    public String getSusheUuidNumber() {
        return susheUuidNumber;
    }


    /**
	 * 设置：宿舍号
	 */
    public void setSusheUuidNumber(String susheUuidNumber) {
        this.susheUuidNumber = susheUuidNumber;
    }
    /**
	 * 获取：宿舍位置
	 */
    public String getSusheAddress() {
        return susheAddress;
    }


    /**
	 * 设置：宿舍位置
	 */
    public void setSusheAddress(String susheAddress) {
        this.susheAddress = susheAddress;
    }
    /**
	 * 获取：最大居住人数
	 */
    public Integer getJuzhuNumber() {
        return juzhuNumber;
    }


    /**
	 * 设置：最大居住人数
	 */
    public void setJuzhuNumber(Integer juzhuNumber) {
        this.juzhuNumber = juzhuNumber;
    }
    /**
	 * 获取：宿舍详情
	 */
    public String getSusheContent() {
        return susheContent;
    }


    /**
	 * 设置：宿舍详情
	 */
    public void setSusheContent(String susheContent) {
        this.susheContent = susheContent;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：录入时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
