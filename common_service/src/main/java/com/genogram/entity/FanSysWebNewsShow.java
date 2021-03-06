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
 * 联谊会web菜单基础表,文章位置表
 * </p>
 *
 * @author wangwei
 * @since 2018-11-17
 */
@TableName("fan_sys_web_news_show")
public class FanSysWebNewsShow extends Model<FanSysWebNewsShow> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * site_id和menuid组合
     */
    @TableField("show_id")
    private Integer showId;
    /**
     * 网站id
     */
    @TableField("site_id")
    private Integer siteId;
    @TableField("menu_id")
    private Integer menuId;
    /**
     * 菜单级数
     */
    @TableField("tree_num")
    private Integer treeNum;
    /**
     * 栏目名称
     */
    @TableField("menu_name")
    private String menuName;
    /**
     * 父ID
     */
    @TableField("parent_id")
    private Integer parentId;
    /**
     * 前台2级栏目文章列表API
     */
    @TableField("api_url")
    private String apiUrl;
    /**
     * 后台2级栏目文章列表API
     */
    @TableField("api_admin_url")
    private String apiAdminUrl;
    /**
     * 排列顺序
     */
    @TableField("order_index")
    private Integer orderIndex;
    /**
     * 菜单类型
     */
    @TableField("menu_code")
    private String menuCode;
    /**
     * 菜单种类
     */
    @TableField("menu_type")
    private String menuType;
    /**
     * 是否是admin显示菜单(0:否;1:是)
     */
    @TableField("is_admin")
    private Integer isAdmin;
    /**
     * 是否是admin显示菜单(0:否;1:是)
     */
    @TableField("is_web")
    private Integer isWeb;
    /**
     * 是否手动添加的子栏目;0:否;1是
     */
    private Integer istatic;
    /**
     * 备注
     */
    private String comments;
    @TableField("create_time")
    private Date createTime;
    @TableField("create_user")
    private Integer createUser;
    @TableField("update_time")
    private Date updateTime;
    @TableField("update_user")
    private Integer updateUser;


    public Integer getId() {
        return id;
    }

    public FanSysWebNewsShow setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getShowId() {
        return showId;
    }

    public FanSysWebNewsShow setShowId(Integer showId) {
        this.showId = showId;
        return this;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public FanSysWebNewsShow setSiteId(Integer siteId) {
        this.siteId = siteId;
        return this;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public FanSysWebNewsShow setMenuId(Integer menuId) {
        this.menuId = menuId;
        return this;
    }

    public Integer getTreeNum() {
        return treeNum;
    }

    public FanSysWebNewsShow setTreeNum(Integer treeNum) {
        this.treeNum = treeNum;
        return this;
    }

    public String getMenuName() {
        return menuName;
    }

    public FanSysWebNewsShow setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public Integer getParentId() {
        return parentId;
    }

    public FanSysWebNewsShow setParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public FanSysWebNewsShow setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
        return this;
    }

    public String getApiAdminUrl() {
        return apiAdminUrl;
    }

    public FanSysWebNewsShow setApiAdminUrl(String apiAdminUrl) {
        this.apiAdminUrl = apiAdminUrl;
        return this;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public FanSysWebNewsShow setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
        return this;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public FanSysWebNewsShow setMenuCode(String menuCode) {
        this.menuCode = menuCode;
        return this;
    }

    public String getMenuType() {
        return menuType;
    }

    public FanSysWebNewsShow setMenuType(String menuType) {
        this.menuType = menuType;
        return this;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public FanSysWebNewsShow setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }

    public Integer getIsWeb() {
        return isWeb;
    }

    public FanSysWebNewsShow setIsWeb(Integer isWeb) {
        this.isWeb = isWeb;
        return this;
    }

    public Integer getIstatic() {
        return istatic;
    }

    public FanSysWebNewsShow setIstatic(Integer istatic) {
        this.istatic = istatic;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public FanSysWebNewsShow setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public FanSysWebNewsShow setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public FanSysWebNewsShow setCreateUser(Integer createUser) {
        this.createUser = createUser;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public FanSysWebNewsShow setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public FanSysWebNewsShow setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FanSysWebNewsShow{" +
        ", id=" + id +
        ", showId=" + showId +
        ", siteId=" + siteId +
        ", menuId=" + menuId +
        ", treeNum=" + treeNum +
        ", menuName=" + menuName +
        ", parentId=" + parentId +
        ", apiUrl=" + apiUrl +
        ", apiAdminUrl=" + apiAdminUrl +
        ", orderIndex=" + orderIndex +
        ", menuCode=" + menuCode +
        ", menuType=" + menuType +
        ", isAdmin=" + isAdmin +
        ", isWeb=" + isWeb +
        ", istatic=" + istatic +
        ", comments=" + comments +
        ", createTime=" + createTime +
        ", createUser=" + createUser +
        ", updateTime=" + updateTime +
        ", updateUser=" + updateUser +
        "}";
    }
}
