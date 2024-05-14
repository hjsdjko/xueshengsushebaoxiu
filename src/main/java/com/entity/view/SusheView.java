package com.entity.view;

import com.entity.SusheEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 宿舍
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("sushe")
public class SusheView extends SusheEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 楼栋的值
		*/
		private String loudongValue;
		/**
		* 单元的值
		*/
		private String danyuanValue;



	public SusheView() {

	}

	public SusheView(SusheEntity susheEntity) {
		try {
			BeanUtils.copyProperties(this, susheEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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










}
