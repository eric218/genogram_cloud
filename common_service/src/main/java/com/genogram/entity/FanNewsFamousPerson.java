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
 * 联谊会-家族名人-家族长老-家族栋梁-组织架构
 * </p>
 *
 * @author wangwei
 * @since 2018-11-13
 */
@TableName("fan_news_famous_person")
public class FanNewsFamousPerson extends Model<FanNewsFamousPerson> {

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
     * 人名
     */
    @TableField("person_name")
    private String personName;
    /**
     * 人物简介
     */
    @TableField("person_summary")
    private String personSummary;
    /**
     * 头像图片位置
     */
    @TableField("pic_file_src")
    private String picFileSrc;
    /**
     * 头像名
     */
    @TableField("pic_file_name")
    private String picFileName;
    /**
     * 查看数
     */
    @TableField("visit_num")
    private Integer visitNum;
    /**
     * 状态(0:删除;1:发布;3:不显示)
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

    public FanNewsFamousPerson setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getShowId() {
        return showId;
    }

    public FanNewsFamousPerson setShowId(Integer showId) {
        this.showId = showId;
        return this;
    }

    public String getPersonName() {
        return personName;
    }

    public FanNewsFamousPerson setPersonName(String personName) {
        this.personName = personName;
        return this;
    }

    public String getPersonSummary() {
        return personSummary;
    }

    public FanNewsFamousPerson setPersonSummary(String personSummary) {
        this.personSummary = personSummary;
        return this;
    }

    public String getPicFileSrc() {
        return picFileSrc;
    }

    public FanNewsFamousPerson setPicFileSrc(String picFileSrc) {
        this.picFileSrc = picFileSrc;
        return this;
    }

    public String getPicFileName() {
        return picFileName;
    }

    public FanNewsFamousPerson setPicFileName(String picFileName) {
        this.picFileName = picFileName;
        return this;
    }

    public Integer getVisitNum() {
        return visitNum;
    }

    public FanNewsFamousPerson setVisitNum(Integer visitNum) {
        this.visitNum = visitNum;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public FanNewsFamousPerson setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public FanNewsFamousPerson setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public FanNewsFamousPerson setCreateUser(Integer createUser) {
        this.createUser = createUser;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public FanNewsFamousPerson setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public FanNewsFamousPerson setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FanNewsFamousPerson{" +
        ", id=" + id +
        ", showId=" + showId +
        ", personName=" + personName +
        ", personSummary=" + personSummary +
        ", picFileSrc=" + picFileSrc +
        ", picFileName=" + picFileName +
        ", visitNum=" + visitNum +
        ", status=" + status +
        ", createTime=" + createTime +
        ", createUser=" + createUser +
        ", updateTime=" + updateTime +
        ", updateUser=" + updateUser +
        "}";
    }
}
