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
 * 联谊会-记录家族-家族动态,家族通告文章表
 * </p>
 *
 * @author wangwei
 * @since 2018-11-26
 */
@TableName("fan_news_family_record")
public class FanNewsFamilyRecord extends Model<FanNewsFamilyRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
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
     * 查看数
     */
    @TableField("visit_num")
    private Integer visitNum;
    /**
     * 状态(0:删除;1:已发布;2:草稿3:不显示)
     */
    private Integer status;
    /**
     * 是否置顶(0:不置顶;1.置顶;)
     */
    @TableField("is_top")
    private Integer isTop;
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

    public FanNewsFamilyRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getShowId() {
        return showId;
    }

    public FanNewsFamilyRecord setShowId(Integer showId) {
        this.showId = showId;
        return this;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public FanNewsFamilyRecord setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
        return this;
    }

    public String getNewsText() {
        return newsText;
    }

    public FanNewsFamilyRecord setNewsText(String newsText) {
        this.newsText = newsText;
        return this;
    }

    public Integer getVisitNum() {
        return visitNum;
    }

    public FanNewsFamilyRecord setVisitNum(Integer visitNum) {
        this.visitNum = visitNum;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public FanNewsFamilyRecord setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public FanNewsFamilyRecord setIsTop(Integer isTop) {
        this.isTop = isTop;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public FanNewsFamilyRecord setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public FanNewsFamilyRecord setCreateUser(Integer createUser) {
        this.createUser = createUser;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public FanNewsFamilyRecord setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public FanNewsFamilyRecord setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FanNewsFamilyRecord{" +
        ", id=" + id +
        ", showId=" + showId +
        ", newsTitle=" + newsTitle +
        ", newsText=" + newsText +
        ", visitNum=" + visitNum +
        ", status=" + status +
        ", isTop=" + isTop +
        ", createTime=" + createTime +
        ", createUser=" + createUser +
        ", updateTime=" + updateTime +
        ", updateUser=" + updateUser +
        "}";
    }
}
