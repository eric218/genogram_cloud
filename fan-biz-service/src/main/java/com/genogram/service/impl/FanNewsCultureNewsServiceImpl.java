package com.genogram.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.genogram.entity.AllUserLogin;
import com.genogram.entity.AllUserReg;
import com.genogram.entity.FanNewsCultureNews;
import com.genogram.entity.FanNewsUploadFile;
import com.genogram.entityvo.FamilyCultureVo;
import com.genogram.entityvo.NewsDetailVo;
import com.genogram.mapper.AllUserLoginMapper;
import com.genogram.mapper.AllUserRegMapper;
import com.genogram.mapper.FanNewsCultureNewsMapper;
import com.genogram.mapper.FanNewsUploadFileMapper;
import com.genogram.service.IAllUserLoginService;
import com.genogram.service.IFanNewsCultureNewsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.genogram.service.IFanNewsUploadFileService;
import com.genogram.service.IUploadFileService;
import com.genogram.unit.DateUtil;
import com.genogram.unit.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 联谊会-家族文化文章表 服务实现类
 * </p>
 *
 * @author wangwei
 * @since 2018-11-05
 */
@Service
public class FanNewsCultureNewsServiceImpl extends ServiceImpl<FanNewsCultureNewsMapper, FanNewsCultureNews> implements IFanNewsCultureNewsService {

    @Autowired
    private IFanNewsUploadFileService fanNewsUploadFileService;

    @Autowired
    private IAllUserLoginService allUserLoginService;

    @Autowired
    private IUploadFileService uploadFileService;

    @Override
    public Page<FamilyCultureVo> getFamilyCulturePage(Wrapper<FanNewsCultureNews> entity, Integer pageNo, Integer pageSize) {
        //返回新VO的集合
        List<FamilyCultureVo> familyCultureVoList=new ArrayList<>();

        //分页查询文章主表
        Page<FanNewsCultureNews> fanNewsCultureNews =this.selectPage(new Page<FanNewsCultureNews>(pageNo, pageSize), entity);

        //得到文件当前页list集合
        List<FanNewsCultureNews> list = fanNewsCultureNews.getRecords();
        //判断改集合是否为空,如果是直接返回结果
        if(list.size()==0){
            return null;
        }

        //得到所有文章id
        List newsids =  new ArrayList<>();
        list.forEach(( news)->{
            newsids.add(news.getId());
        });

        //查询图片
        Wrapper<FanNewsUploadFile> uploadentity = new EntityWrapper<FanNewsUploadFile>();
        uploadentity.eq("show_id", list.get(0).getShowId());
        //  1 表示图片为显示状态
        uploadentity.eq("status", 1);
        uploadentity.in("news_id",newsids);
        //查询所有文章id下的图片附件
        List<FanNewsUploadFile> files =  fanNewsUploadFileService.selectList(uploadentity);


        //遍历主表文章集合,赋值新对象vo
        list.forEach(( news)->{
            FamilyCultureVo familyCultureVo=new FamilyCultureVo();

            //存储新对象
            BeanUtils.copyProperties(news,familyCultureVo);

            //判断改图片文章id是否一样
            List<FanNewsUploadFile> fanNewsUploadFile=new ArrayList<>();

            files.forEach(( data)->{
                if(news.getId().equals(data.getNewsId())){
                    fanNewsUploadFile.add(data);
                }
            });

            //存储图片list集合
            familyCultureVo.setFanNewsUploadFileList(fanNewsUploadFile);


            //存储到新的集合中
            familyCultureVoList.add(familyCultureVo);
        });

        //重新设置page对象
        Page<FamilyCultureVo> mapPage = new Page<>(pageNo,pageSize);
        mapPage.setRecords(familyCultureVoList);
        mapPage.setSize(fanNewsCultureNews.getSize());
        mapPage.setTotal(fanNewsCultureNews.getTotal());

        return mapPage;
    }

    /**
     *联谊会家族文化详情查询
     *@Author: yuzhou
     *@Date: 2018-11-09
     *@Time: 16:22
     *@Param:
     *@return:
     *@Description:
    */
    @Override
    public NewsDetailVo getFamilyCultureDetail(Integer id) {

        //根据Id查出文章详情
        FanNewsCultureNews fanNewsCultureNews=  this.selectById(id);
        if (fanNewsCultureNews==null){
            return null;
        }

        //查询图片
        Wrapper<FanNewsUploadFile> uploadentity = new EntityWrapper<FanNewsUploadFile>();
        uploadentity.eq("show_id", fanNewsCultureNews.getShowId());
        uploadentity.eq("news_id",id);
        //查询所有文章id下的图片附件
        List<FanNewsUploadFile> files =  fanNewsUploadFileService.selectList(uploadentity);

        //查出名称
        AllUserLogin updateUser = allUserLoginService.selectById(fanNewsCultureNews.getUpdateUser());
        AllUserLogin createUser = allUserLoginService.selectById(fanNewsCultureNews.getCreateUser());

        //返回新VO的集合赋值新对象vo
        NewsDetailVo newsDetail=new NewsDetailVo();

        //调用方法封装集合
        BeanUtils.copyProperties(fanNewsCultureNews,newsDetail);
        //存储图片list集合
        newsDetail.setFanNewsUploadFileList(files);
        //存储作者名称时间
        newsDetail.setUpdateTimeLong(fanNewsCultureNews.getUpdateTime().getTime());
        newsDetail.setCreateTimeLong(fanNewsCultureNews.getCreateTime().getTime());
        newsDetail.setCreateUserName(createUser.getRealName());
        newsDetail.setCreateUserName(updateUser.getRealName());
        return newsDetail;
    }

    /**
     *家族文化后台添加
     *@Author: yuzhou
     *@Date: 2018-11-09
     *@Time: 16:23
     *@Param:
     *@return:
     *@Description:
    */
    @Override
    public boolean addOrUpdateCulture(FanNewsCultureNews fanNewsCultureNews,String fileNames) {
        /*boolean isAdd=this.insertOrUpdate(fanNewsCultureNews);*/
        //生成时间
        Timestamp format = DateUtil.getCurrentTimeStamp();
        if(fanNewsCultureNews.getId()==null){
            //存入创建时间
            fanNewsCultureNews.setCreateTime(format);
            fanNewsCultureNews.setCreateUser(null);
            //存入修改时间
            fanNewsCultureNews.setUpdateTime(format);
            fanNewsCultureNews.setUpdateUser(null);
        }else{
            //存入修改时间
            fanNewsCultureNews.setUpdateTime(format);
            fanNewsCultureNews.setUpdateUser(null);
        }
        //插入数据
        boolean result = this.insert(fanNewsCultureNews);
        //存储图片
        if(result){
            uploadFileService.storageFanFile(fileNames,fanNewsCultureNews.getId(),fanNewsCultureNews.getShowId());
        }
        return result;
    }

    /**
     *联谊会家族文化后台删除
     *@Author: yuzhou
     *@Date: 2018-11-10
     *@Time: 11:58
     *@Param:
     *@return:
     *@Description:
    */
    @Override
        public Boolean deleteCulturById(Integer id, int status) {
        FanNewsCultureNews fanNewsCultureNews = this.selectById(id);
        fanNewsCultureNews.setStatus(status);
        fanNewsCultureNews.setUpdateTime(DateUtil.getCurrentTimeStamp());
        //修改人待写
        boolean result = this.updateAllColumnById(fanNewsCultureNews);
        return result;
    }

    /**
     *联谊会家族文化前台增加查看数
     *@Author: yuzhou
     *@Date: 2018-11-12
     *@Time: 13:49
     *@Param:
     *@return:
     *@Description:
    */
    @Override
    public void addVisitNum(Integer id) {
        //查出详情
        FanNewsCultureNews fanNewsCultureNews = this.selectById(id);
        //查看数加一
        fanNewsCultureNews.setVisitNum(fanNewsCultureNews.getVisitNum()+1);
         this.updateAllColumnById(fanNewsCultureNews);
    }
}
