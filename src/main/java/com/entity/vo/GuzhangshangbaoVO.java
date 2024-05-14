package com.entity.vo;

import com.entity.GuzhangshangbaoEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 故障上报
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("guzhangshangbao")
public class GuzhangshangbaoVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 学生
     */

    @TableField(value = "xuesheng_id")
    private Integer xueshengId;


    /**
     * 维修人员
     */

    @TableField(value = "weixiuyuan_id")
    private Integer weixiuyuanId;


    /**
     * 上报编号
     */

    @TableField(value = "guzhangshangbao_uuid_number")
    private String guzhangshangbaoUuidNumber;


    /**
     * 物品分类
     */

    @TableField(value = "wupin_types")
    private Integer wupinTypes;


    /**
     * 故障分类
     */

    @TableField(value = "guzhangshangbao_types")
    private Integer guzhangshangbaoTypes;


    /**
     * 报修位置
     */

    @TableField(value = "guzhangshangbao_address")
    private String guzhangshangbaoAddress;


    /**
     * 上报时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 上报详情
     */

    @TableField(value = "forum_content")
    private String forumContent;


    /**
     * 维修状态
     */

    @TableField(value = "weixiuzhuangtai_types")
    private Integer weixiuzhuangtaiTypes;


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
	 * 设置：学生
	 */
    public Integer getXueshengId() {
        return xueshengId;
    }


    /**
	 * 获取：学生
	 */

    public void setXueshengId(Integer xueshengId) {
        this.xueshengId = xueshengId;
    }
    /**
	 * 设置：维修人员
	 */
    public Integer getWeixiuyuanId() {
        return weixiuyuanId;
    }


    /**
	 * 获取：维修人员
	 */

    public void setWeixiuyuanId(Integer weixiuyuanId) {
        this.weixiuyuanId = weixiuyuanId;
    }
    /**
	 * 设置：上报编号
	 */
    public String getGuzhangshangbaoUuidNumber() {
        return guzhangshangbaoUuidNumber;
    }


    /**
	 * 获取：上报编号
	 */

    public void setGuzhangshangbaoUuidNumber(String guzhangshangbaoUuidNumber) {
        this.guzhangshangbaoUuidNumber = guzhangshangbaoUuidNumber;
    }
    /**
	 * 设置：物品分类
	 */
    public Integer getWupinTypes() {
        return wupinTypes;
    }


    /**
	 * 获取：物品分类
	 */

    public void setWupinTypes(Integer wupinTypes) {
        this.wupinTypes = wupinTypes;
    }
    /**
	 * 设置：故障分类
	 */
    public Integer getGuzhangshangbaoTypes() {
        return guzhangshangbaoTypes;
    }


    /**
	 * 获取：故障分类
	 */

    public void setGuzhangshangbaoTypes(Integer guzhangshangbaoTypes) {
        this.guzhangshangbaoTypes = guzhangshangbaoTypes;
    }
    /**
	 * 设置：报修位置
	 */
    public String getGuzhangshangbaoAddress() {
        return guzhangshangbaoAddress;
    }


    /**
	 * 获取：报修位置
	 */

    public void setGuzhangshangbaoAddress(String guzhangshangbaoAddress) {
        this.guzhangshangbaoAddress = guzhangshangbaoAddress;
    }
    /**
	 * 设置：上报时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：上报时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：上报详情
	 */
    public String getForumContent() {
        return forumContent;
    }


    /**
	 * 获取：上报详情
	 */

    public void setForumContent(String forumContent) {
        this.forumContent = forumContent;
    }
    /**
	 * 设置：维修状态
	 */
    public Integer getWeixiuzhuangtaiTypes() {
        return weixiuzhuangtaiTypes;
    }


    /**
	 * 获取：维修状态
	 */

    public void setWeixiuzhuangtaiTypes(Integer weixiuzhuangtaiTypes) {
        this.weixiuzhuangtaiTypes = weixiuzhuangtaiTypes;
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
