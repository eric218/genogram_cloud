package com.genogram.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.genogram.entity.AllUserReg;
import com.genogram.entity.FanNewsCultureNews;
import com.genogram.entity.FanNewsUploadFile;
import com.genogram.entityvo.FamilyCultureVo;
import com.genogram.entityvo.NewsDetailVo;
import com.genogram.mapper.AllUserRegMapper;
import com.genogram.mapper.FanNewsCultureNewsMapper;
import com.genogram.mapper.FanNewsUploadFileMapper;
import com.genogram.service.IFanNewsCultureNewsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private FanNewsUploadFileMapper fanNewsUploadFileMapper;

    @Autowired
    private AllUserRegMapper allUserRegMapper;

    @Override
    public Page<FamilyCultureVo> getFamilyCulturePage(Integer showId, Integer status, Integer pageNo, Integer pageSize) {
        //返回新VO的集合
        List<FamilyCultureVo> familyCultureVoList=new ArrayList<>();

        //查询文章信息的条件
        Wrapper<FanNewsCultureNews> entity = new EntityWrapper<FanNewsCultureNews>();
        entity.eq("show_id", showId);
        entity.eq("status", status);
        entity.orderBy("create_time", false);
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
        uploadentity.eq("show_id", showId);
        uploadentity.eq("status", status);
        uploadentity.in("news_id",newsids);
        //查询所有文章id下的图片附件
        List<FanNewsUploadFile> files =  fanNewsUploadFileMapper.selectList(uploadentity);


        //遍历主表文章集合,赋值新对象vo
        list.forEach(( news)->{
            FamilyCultureVo familyCultureVo=new FamilyCultureVo();
            familyCultureVo.setId(news.getId());
            familyCultureVo.setShowId(news.getShowId());
            familyCultureVo.setNewsTitle(news.getNewsTitle());
            familyCultureVo.setNewsText(news.getNewsText());
            familyCultureVo.setVisitNum(news.getVisitNum());
            familyCultureVo.setStatus(news.getStatus());
            familyCultureVo.setCreateTime(news.getCreateTime());
            familyCultureVo.setCreateUser(news.getCreateUser());
            familyCultureVo.setUpdateTime(news.getUpdateTime());
            familyCultureVo.setUpdateUser(news.getUpdateUser());


            //判断改图片文章id是否一样
            List<FanNewsUploadFile> fanNewsUploadFile=new ArrayList<>();

            files.forEach(( data)->{
                if(news.getId()==data.getNewsId()){
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

    //联谊会家族文化详情查询
    @Override
    public NewsDetailVo getFamilyCultureDetail(Integer showId, Integer id) {

        //根据Id查出文章详情
        FanNewsCultureNews fanNewsCultureNews=  this.selectById(id);

        //查询图片
        Wrapper<FanNewsUploadFile> uploadentity = new EntityWrapper<FanNewsUploadFile>();
        uploadentity.eq("show_id", showId);
        uploadentity.eq("news_id",id);
        //查询所有文章id下的图片附件
        List<FanNewsUploadFile> files =  fanNewsUploadFileMapper.selectList(uploadentity);

        AllUserReg allUserReg = allUserRegMapper.selectById(fanNewsCultureNews.getCreateUser());

        /*//查出名称
        Wrapper<AllUserReg> entity = new EntityWrapper<AllUserReg>();
        entity.eq("user_id", fanNewsCultureNews.getCreateUser());*/

        //返回新VO的集合赋值新对象vo
        NewsDetailVo newsDetail=new NewsDetailVo();
        newsDetail.setId(fanNewsCultureNews.getId());
        newsDetail.setShowId(fanNewsCultureNews.getShowId());
        newsDetail.setNewsTitle(fanNewsCultureNews.getNewsTitle());
        newsDetail.setNewsText(fanNewsCultureNews.getNewsText());
        newsDetail.setVisitNum(fanNewsCultureNews.getVisitNum());
        newsDetail.setStatus(fanNewsCultureNews.getStatus());
        newsDetail.setCreateTime(fanNewsCultureNews.getCreateTime());
        newsDetail.setCreateUser(fanNewsCultureNews.getCreateUser());
        newsDetail.setUpdateTime(fanNewsCultureNews.getUpdateTime());
        newsDetail.setUpdateUser(fanNewsCultureNews.getUpdateUser());
        //存储图片list集合
        newsDetail.setFanNewsUploadFileList(files);
        //存储作者名称
        newsDetail.setUserName(allUserReg.getRealName());
        return newsDetail;
    }
}
