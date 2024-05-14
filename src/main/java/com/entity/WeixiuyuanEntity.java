package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 维修人员
 *
 * @author 
 * @email
 */
@TableName("weixiuyuan")
public class WeixiuyuanEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public WeixiuyuanEntity() {

	}

	public WeixiuyuanEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 账号
     */
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @TableField(value = "password")

    private String password;


    /**
     * 工号
     */
    @TableField(value = "weixiuyuan_uuid_number")

    private String weixiuyuanUuidNumber;


    /**
     * 维修员姓名
     */
    @TableField(value = "weixiuyuan_name")

    private String weixiuyuanName;


    /**
     * 性别
     */
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 工作年限
     */
    @TableField(value = "weixiuyuan_nianxian")

    private String weixiuyuanNianxian;


    /**
     * 联系方式
     */
    @TableField(value = "weixiuyuan_phone")

    private String weixiuyuanPhone;


    /**
     * 照片
     */
    @TableField(value = "weixiuyuan_photo")

    private String weixiuyuanPhoto;


    /**
     * 维修人员介绍
     */
    @TableField(value = "weixiuyuan_content")

    private String weixiuyuanContent;


    /**
     * 假删
     */
    @TableField(value = "weixiuyuan_delete")

    private Integer weixiuyuanDelete;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 设置：账号
	 */
    public String getUsername() {
        return username;
    }
    /**
	 * 获取：账号
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }
    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：工号
	 */
    public String getWeixiuyuanUuidNumber() {
        return weixiuyuanUuidNumber;
    }
    /**
	 * 获取：工号
	 */

    public void setWeixiuyuanUuidNumber(String weixiuyuanUuidNumber) {
        this.weixiuyuanUuidNumber = weixiuyuanUuidNumber;
    }
    /**
	 * 设置：维修员姓名
	 */
    public String getWeixiuyuanName() {
        return weixiuyuanName;
    }
    /**
	 * 获取：维修员姓名
	 */

    public void setWeixiuyuanName(String weixiuyuanName) {
        this.weixiuyuanName = weixiuyuanName;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }
    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：工作年限
	 */
    public String getWeixiuyuanNianxian() {
        return weixiuyuanNianxian;
    }
    /**
	 * 获取：工作年限
	 */

    public void setWeixiuyuanNianxian(String weixiuyuanNianxian) {
        this.weixiuyuanNianxian = weixiuyuanNianxian;
    }
    /**
	 * 设置：联系方式
	 */
    public String getWeixiuyuanPhone() {
        return weixiuyuanPhone;
    }
    /**
	 * 获取：联系方式
	 */

    public void setWeixiuyuanPhone(String weixiuyuanPhone) {
        this.weixiuyuanPhone = weixiuyuanPhone;
    }
    /**
	 * 设置：照片
	 */
    public String getWeixiuyuanPhoto() {
        return weixiuyuanPhoto;
    }
    /**
	 * 获取：照片
	 */

    public void setWeixiuyuanPhoto(String weixiuyuanPhoto) {
        this.weixiuyuanPhoto = weixiuyuanPhoto;
    }
    /**
	 * 设置：维修人员介绍
	 */
    public String getWeixiuyuanContent() {
        return weixiuyuanContent;
    }
    /**
	 * 获取：维修人员介绍
	 */

    public void setWeixiuyuanContent(String weixiuyuanContent) {
        this.weixiuyuanContent = weixiuyuanContent;
    }
    /**
	 * 设置：假删
	 */
    public Integer getWeixiuyuanDelete() {
        return weixiuyuanDelete;
    }
    /**
	 * 获取：假删
	 */

    public void setWeixiuyuanDelete(Integer weixiuyuanDelete) {
        this.weixiuyuanDelete = weixiuyuanDelete;
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

    @Override
    public String toString() {
        return "Weixiuyuan{" +
            "id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", weixiuyuanUuidNumber=" + weixiuyuanUuidNumber +
            ", weixiuyuanName=" + weixiuyuanName +
            ", sexTypes=" + sexTypes +
            ", weixiuyuanNianxian=" + weixiuyuanNianxian +
            ", weixiuyuanPhone=" + weixiuyuanPhone +
            ", weixiuyuanPhoto=" + weixiuyuanPhoto +
            ", weixiuyuanContent=" + weixiuyuanContent +
            ", weixiuyuanDelete=" + weixiuyuanDelete +
            ", createTime=" + createTime +
        "}";
    }
}
