package com.genogram.entityvo;

import com.genogram.entity.ProNewsUploadFile;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProFamilyRecordVo {

    //记录家族图片
    private List<ProNewsUploadFile> proNewsUploadFiles;
    /**
     * 编号
     */
    private Integer id;
    /**
     * 显示位置id(fan_sys_web_news_show_id)
     */
    private Integer showId;
    /**
     * 标题
     */
    private String newsTitle;
    /**
     * 内容
     */
    private String newsText;
    /**
     * 查看数
     */
    private Integer visitNum;
    /**
     * 状态(0:删除;1:已发布;2:草稿3:不显示)
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private Integer createUser;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 修改人
     */
    private Integer updateUser;
}
