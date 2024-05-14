package com.entity.view;

import com.entity.GuzhangshangbaoEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 故障上报
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("guzhangshangbao")
public class GuzhangshangbaoView extends GuzhangshangbaoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 物品分类的值
		*/
		private String wupinValue;
		/**
		* 故障分类的值
		*/
		private String guzhangshangbaoValue;
		/**
		* 维修状态的值
		*/
		private String weixiuzhuangtaiValue;



		//级联表 weixiuyuan
			/**
			* 工号
			*/
			private String weixiuyuanUuidNumber;
			/**
			* 维修员姓名
			*/
			private String weixiuyuanName;
			/**
			* 工作年限
			*/
			private String weixiuyuanNianxian;
			/**
			* 联系方式
			*/
			private String weixiuyuanPhone;
			/**
			* 照片
			*/
			private String weixiuyuanPhoto;
			/**
			* 维修人员介绍
			*/
			private String weixiuyuanContent;
			/**
			* 假删
			*/
			private Integer weixiuyuanDelete;

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

	public GuzhangshangbaoView() {

	}

	public GuzhangshangbaoView(GuzhangshangbaoEntity guzhangshangbaoEntity) {
		try {
			BeanUtils.copyProperties(this, guzhangshangbaoEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 物品分类的值
			*/
			public String getWupinValue() {
				return wupinValue;
			}
			/**
			* 设置： 物品分类的值
			*/
			public void setWupinValue(String wupinValue) {
				this.wupinValue = wupinValue;
			}
			/**
			* 获取： 故障分类的值
			*/
			public String getGuzhangshangbaoValue() {
				return guzhangshangbaoValue;
			}
			/**
			* 设置： 故障分类的值
			*/
			public void setGuzhangshangbaoValue(String guzhangshangbaoValue) {
				this.guzhangshangbaoValue = guzhangshangbaoValue;
			}
			/**
			* 获取： 维修状态的值
			*/
			public String getWeixiuzhuangtaiValue() {
				return weixiuzhuangtaiValue;
			}
			/**
			* 设置： 维修状态的值
			*/
			public void setWeixiuzhuangtaiValue(String weixiuzhuangtaiValue) {
				this.weixiuzhuangtaiValue = weixiuzhuangtaiValue;
			}




















				//级联表的get和set weixiuyuan

					/**
					* 获取： 工号
					*/
					public String getWeixiuyuanUuidNumber() {
						return weixiuyuanUuidNumber;
					}
					/**
					* 设置： 工号
					*/
					public void setWeixiuyuanUuidNumber(String weixiuyuanUuidNumber) {
						this.weixiuyuanUuidNumber = weixiuyuanUuidNumber;
					}

					/**
					* 获取： 维修员姓名
					*/
					public String getWeixiuyuanName() {
						return weixiuyuanName;
					}
					/**
					* 设置： 维修员姓名
					*/
					public void setWeixiuyuanName(String weixiuyuanName) {
						this.weixiuyuanName = weixiuyuanName;
					}

					/**
					* 获取： 工作年限
					*/
					public String getWeixiuyuanNianxian() {
						return weixiuyuanNianxian;
					}
					/**
					* 设置： 工作年限
					*/
					public void setWeixiuyuanNianxian(String weixiuyuanNianxian) {
						this.weixiuyuanNianxian = weixiuyuanNianxian;
					}

					/**
					* 获取： 联系方式
					*/
					public String getWeixiuyuanPhone() {
						return weixiuyuanPhone;
					}
					/**
					* 设置： 联系方式
					*/
					public void setWeixiuyuanPhone(String weixiuyuanPhone) {
						this.weixiuyuanPhone = weixiuyuanPhone;
					}

					/**
					* 获取： 照片
					*/
					public String getWeixiuyuanPhoto() {
						return weixiuyuanPhoto;
					}
					/**
					* 设置： 照片
					*/
					public void setWeixiuyuanPhoto(String weixiuyuanPhoto) {
						this.weixiuyuanPhoto = weixiuyuanPhoto;
					}

					/**
					* 获取： 维修人员介绍
					*/
					public String getWeixiuyuanContent() {
						return weixiuyuanContent;
					}
					/**
					* 设置： 维修人员介绍
					*/
					public void setWeixiuyuanContent(String weixiuyuanContent) {
						this.weixiuyuanContent = weixiuyuanContent;
					}

					/**
					* 获取： 假删
					*/
					public Integer getWeixiuyuanDelete() {
						return weixiuyuanDelete;
					}
					/**
					* 设置： 假删
					*/
					public void setWeixiuyuanDelete(Integer weixiuyuanDelete) {
						this.weixiuyuanDelete = weixiuyuanDelete;
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
