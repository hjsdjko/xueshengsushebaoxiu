package com.entity.view;

import com.entity.SusheRenEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 宿舍人员
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("sushe_ren")
public class SusheRenView extends SusheRenEntity implements Serializable {
    private static final long serialVersionUID = 1L;




		//级联表 sushe
			/**
			* 楼栋
			*/
			private Integer loudongTypes;
				/**
				* 楼栋的值
				*/
				private String loudongValue;
			/**
			* 单元
			*/
			private Integer danyuanTypes;
				/**
				* 单元的值
				*/
				private String danyuanValue;
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

		//级联表 xuesheng
			/**
			* 学号
			*/
			private String xueshengUuidNumber;
			/**
			* 学生姓名
			*/
			private String xueshengName;
			/**
			* 身份证号
			*/
			private String xueshengIdNumber;
			/**
			* 手机号
			*/
			private String xueshengPhone;
			/**
			* 照片
			*/
			private String xueshengPhoto;

	public SusheRenView() {

	}

	public SusheRenView(SusheRenEntity susheRenEntity) {
		try {
			BeanUtils.copyProperties(this, susheRenEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
















				//级联表的get和set sushe

					/**
					* 获取： 楼栋
					*/
					public Integer getLoudongTypes() {
						return loudongTypes;
					}
					/**
					* 设置： 楼栋
					*/
					public void setLoudongTypes(Integer loudongTypes) {
						this.loudongTypes = loudongTypes;
					}


						/**
						* 获取： 楼栋的值
						*/
						public String getLoudongValue() {
							return loudongValue;
						}
						/**
						* 设置： 楼栋的值
						*/
						public void setLoudongValue(String loudongValue) {
							this.loudongValue = loudongValue;
						}

					/**
					* 获取： 单元
					*/
					public Integer getDanyuanTypes() {
						return danyuanTypes;
					}
					/**
					* 设置： 单元
					*/
					public void setDanyuanTypes(Integer danyuanTypes) {
						this.danyuanTypes = danyuanTypes;
					}


						/**
						* 获取： 单元的值
						*/
						public String getDanyuanValue() {
							return danyuanValue;
						}
						/**
						* 设置： 单元的值
						*/
						public void setDanyuanValue(String danyuanValue) {
							this.danyuanValue = danyuanValue;
						}

					/**
					* 获取： 宿舍号
					*/
					public String getSusheUuidNumber() {
						return susheUuidNumber;
					}
					/**
					* 设置： 宿舍号
					*/
					public void setSusheUuidNumber(String susheUuidNumber) {
						this.susheUuidNumber = susheUuidNumber;
					}

					/**
					* 获取： 宿舍位置
					*/
					public String getSusheAddress() {
						return susheAddress;
					}
					/**
					* 设置： 宿舍位置
					*/
					public void setSusheAddress(String susheAddress) {
						this.susheAddress = susheAddress;
					}

					/**
					* 获取： 最大居住人数
					*/
					public Integer getJuzhuNumber() {
						return juzhuNumber;
					}
					/**
					* 设置： 最大居住人数
					*/
					public void setJuzhuNumber(Integer juzhuNumber) {
						this.juzhuNumber = juzhuNumber;
					}

					/**
					* 获取： 宿舍详情
					*/
					public String getSusheContent() {
						return susheContent;
					}
					/**
					* 设置： 宿舍详情
					*/
					public void setSusheContent(String susheContent) {
						this.susheContent = susheContent;
					}










				//级联表的get和set xuesheng

					/**
					* 获取： 学号
					*/
					public String getXueshengUuidNumber() {
						return xueshengUuidNumber;
					}
					/**
					* 设置： 学号
					*/
					public void setXueshengUuidNumber(String xueshengUuidNumber) {
						this.xueshengUuidNumber = xueshengUuidNumber;
					}

					/**
					* 获取： 学生姓名
					*/
					public String getXueshengName() {
						return xueshengName;
					}
					/**
					* 设置： 学生姓名
					*/
					public void setXueshengName(String xueshengName) {
						this.xueshengName = xueshengName;
					}

					/**
					* 获取： 身份证号
					*/
					public String getXueshengIdNumber() {
						return xueshengIdNumber;
					}
					/**
					* 设置： 身份证号
					*/
					public void setXueshengIdNumber(String xueshengIdNumber) {
						this.xueshengIdNumber = xueshengIdNumber;
					}

					/**
					* 获取： 手机号
					*/
					public String getXueshengPhone() {
						return xueshengPhone;
					}
					/**
					* 设置： 手机号
					*/
					public void setXueshengPhone(String xueshengPhone) {
						this.xueshengPhone = xueshengPhone;
					}

					/**
					* 获取： 照片
					*/
					public String getXueshengPhoto() {
						return xueshengPhoto;
					}
					/**
					* 设置： 照片
					*/
					public void setXueshengPhoto(String xueshengPhoto) {
						this.xueshengPhoto = xueshengPhoto;
					}



}
