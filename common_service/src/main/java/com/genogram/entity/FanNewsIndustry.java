package com.genogram.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 联谊会-家族产业
 * </p>
 *
 * @author wangwei
 * @since 2018-11-14
 */
@TableName("fan_news_industry")
public class FanNewsIndustry extends Model<FanNewsIndustry> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 显示位置id(fan_sys_web_news_show_id)
     */
    @TableField("show_id")
    private Integer showId;
    /**
     * 标题
     */
    @TableField("news_title")
    private String newsTitle;
    /**
     * 内容
     */
    @TableField("news_text")
    private String newsText;
    /**
     * 家族产业具体地址
     */
    @TableField("industry_location")
    private String industryLocation;
    /**
     * 查看数
     */
    @TableField("visit_num")
    private Integer visitNum;
    /**
     * 状态(0:删除;1:已发布;2:草稿3:不显示)
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建人
     */
    @TableField("create_user")
    private Integer createUser;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 修改人
     */
    @TableField("update_user")
    private Integer updateUser;


    public Integer getId() {
        return id;
    }

    public FanNewsIndustry setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getShowId() {
        return showId;
    }

    public FanNewsIndustry setShowId(Integer showId) {
        this.showId = showId;
        return this;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public FanNewsIndustry setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
        return this;
    }

    public String getNewsText() {
        return newsText;
    }

    public FanNewsIndustry setNewsText(String newsText) {
        this.newsText = newsText;
        return this;
    }

    public String getIndustryLocation() {
        return industryLocation;
    }

    public FanNewsIndustry setIndustryLocation(String industryLocation) {
        this.industryLocation = industryLocation;
        return this;
    }

    public Integer getVisitNum() {
        return visitNum;
    }

    public FanNewsIndustry setVisitNum(Integer visitNum) {
        this.visitNum = visitNum;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public FanNewsIndustry setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public FanNewsIndustry setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public FanNewsIndustry setCreateUser(Integer createUser) {
        this.createUser = createUser;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public FanNewsIndustry setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public FanNewsIndustry setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FanNewsIndustry{" +
        ", id=" + id +
        ", showId=" + showId +
        ", newsTitle=" + newsTitle +
        ", newsText=" + newsText +
        ", industryLocation=" + industryLocation +
        ", visitNum=" + visitNum +
        ", status=" + status +
        ", createTime=" + createTime +
        ", createUser=" + createUser +
        ", updateTime=" + updateTime +
        ", updateUser=" + updateUser +
        "}";
    }
}
