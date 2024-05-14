package com.entity.vo;

import com.entity.SusheEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 宿舍
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("sushe")
public class SusheVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 楼栋
     */

    @TableField(value = "loudong_types")
    private Integer loudongTypes;


    /**
     * 单元
     */

    @TableField(value = "danyuan_types")
    private Integer danyuanTypes;


    /**
     * 宿舍号
     */

    @TableField(value = "sushe_uuid_number")
    private String susheUuidNumber;


    /**
     * 宿舍位置
     */

    @TableField(value = "sushe_address")
    private String susheAddress;


    /**
     * 最大居住人数
     */

    @TableField(value = "juzhu_number")
    private Integer juzhuNumber;


    /**
     * 宿舍详情
     */

    @TableField(value = "sushe_content")
    private String susheContent;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：楼栋
	 */
    public Integer getLoudongTypes() {
        return loudongTypes;
    }


    /**
	 * 获取：楼栋
	 */

    public void setLoudongTypes(Integer loudongTypes) {
        this.loudongTypes = loudongTypes;
    }
    /**
	 * 设置：单元
	 */
    public Integer getDanyuanTypes() {
        return danyuanTypes;
    }


    /**
	 * 获取：单元
	 */

    public void setDanyuanTypes(Integer danyuanTypes) {
        this.danyuanTypes = danyuanTypes;
    }
    /**
	 * 设置：宿舍号
	 */
    public String getSusheUuidNumber() {
        return susheUuidNumber;
    }


    /**
	 * 获取：宿舍号
	 */

    public void setSusheUuidNumber(String susheUuidNumber) {
        this.susheUuidNumber = susheUuidNumber;
    }
    /**
	 * 设置：宿舍位置
	 */
    public String getSusheAddress() {
        return susheAddress;
    }


    /**
	 * 获取：宿舍位置
	 */

    public void setSusheAddress(String susheAddress) {
        this.susheAddress = susheAddress;
    }
    /**
	 * 设置：最大居住人数
	 */
    public Integer getJuzhuNumber() {
        return juzhuNumber;
    }


    /**
	 * 获取：最大居住人数
	 */

    public void setJuzhuNumber(Integer juzhuNumber) {
        this.juzhuNumber = juzhuNumber;
    }
    /**
	 * 设置：宿舍详情
	 */
    public String getSusheContent() {
        return susheContent;
    }


    /**
	 * 获取：宿舍详情
	 */

    public void setSusheContent(String susheContent) {
        this.susheContent = susheContent;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
