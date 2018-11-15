package com.genogram.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.genogram.entity.*;
import com.genogram.entityvo.FamilyPersonVo;
import com.genogram.mapper.FanNewsFamousPersonMapper;
import com.genogram.mapper.FanNewsUploadFileMapper;
import com.genogram.service.IAllUserLoginService;
import com.genogram.service.IFanNewsFamousPersonService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.genogram.service.IFanNewsUploadFileService;
import com.genogram.service.IUploadFileService;
import com.genogram.unit.DateUtil;
import com.genogram.unit.StringsUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 联谊会-家族名人-家族长老-家族栋梁-组织架构 服务实现类
 * </p>
 *
 * @author wangwei
 * @since 2018-11-05
 */
@Service
public class FanNewsFamousPersonServiceImpl extends ServiceImpl<FanNewsFamousPersonMapper, FanNewsFamousPerson> implements IFanNewsFamousPersonService {
    @Autowired
    private FanNewsUploadFileMapper fanNewsUploadFileMapper;

    @Autowired
    private IUploadFileService iuploadFileService;
    @Autowired
    private IFanNewsUploadFileService fanNewsUploadFileService;

    @Autowired
    private IAllUserLoginService allUserLoginService;

    @Override
    public Page<FamilyPersonVo> getFamilyPersionPage(Integer showId, Integer status, Integer pageNo, Integer pageSize) {
        //返回新VO的集合
        List<FamilyPersonVo> familyPersonVoList=new ArrayList<>();

        Wrapper<FanNewsFamousPerson> entity = new EntityWrapper<FanNewsFamousPerson>();
        entity.eq("show_id", showId);
        entity.eq("status", status);
        entity.orderBy("create_time", false);
        //分页查询文章主表
        Page<FanNewsFamousPerson> fanNewsFamousPerson =this.selectPage(new Page<FanNewsFamousPerson>(pageNo, pageSize), entity);

        //得到文件当前页list集合
        List<FanNewsFamousPerson> list = fanNewsFamousPerson.getRecords();
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
            FamilyPersonVo familyPersonVo=new FamilyPersonVo();
            familyPersonVo.setId(news.getId());
            familyPersonVo.setShowId(news.getShowId());
            familyPersonVo.setPersonName(news.getPersonName());
            familyPersonVo.setPersonSummary(news.getPersonSummary());
            familyPersonVo.setVisitNum(news.getVisitNum());
            familyPersonVo.setStatus(news.getStatus());
            familyPersonVo.setCreateTime(news.getCreateTime());
            familyPersonVo.setCreateUser(news.getCreateUser());
            familyPersonVo.setUpdateTime(news.getUpdateTime());
            familyPersonVo.setUpdateUser(news.getUpdateUser());


            //判断改图片文章id是否一样
            List<FanNewsUploadFile> fanNewsUploadFile=new ArrayList<>();

            files.forEach(( data)->{
                if(news.getId().equals(data.getNewsId())){
                    fanNewsUploadFile.add(data);
                }
            });

            //存储图片list集合
            familyPersonVo.setFanNewsUploadFileList(fanNewsUploadFile);


            //存储到新的集合中
            familyPersonVoList.add(familyPersonVo);

        });
        //重新设置page对象
        Page<FamilyPersonVo> mapPage = new Page<>(pageNo,pageSize);
        mapPage.setRecords(familyPersonVoList);
        mapPage.setSize(fanNewsFamousPerson.getSize());
        mapPage.setTotal(fanNewsFamousPerson.getTotal());
        return mapPage;
    }

    /**
     * 后台查询
     * @param entity
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<FamilyPersonVo> getFamilyPersionPages(Wrapper<FanNewsFamousPerson> entity, Integer pageNo, Integer pageSize) {
        //返回新VO的集合
        List<FamilyPersonVo> familyPersionVoList=new ArrayList<>();


        //分页查询产业文章主表
        Page<FanNewsFamousPerson> familyPersonVoPage =this.selectPage(new Page<FanNewsFamousPerson>(pageNo, pageSize),entity);
        //得到文件当前页list集合
        List<FanNewsFamousPerson> list = familyPersonVoPage.getRecords();
        //判断改集合是否为空,如果是直接返回结果
        if(list.size()==0){
            return null;
        }

        //得到所有产业文章id
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
            FamilyPersonVo familyPersonVo=new FamilyPersonVo();
            //调用方法封装集合
            BeanUtils.copyProperties(news,familyPersonVo);
            //判断改图片文章id是否一样
            List<FanNewsUploadFile> fanNewsUploadFile=new ArrayList<>();

            files.forEach(( data)->{
                if(news.getId().equals(data.getNewsId())){
                    fanNewsUploadFile.add(data);
                }
            });

            //存储图片list集合
            familyPersonVo.setFanNewsUploadFileList(fanNewsUploadFile);


            //存储到新的集合中
            familyPersionVoList.add(familyPersonVo);
        });

        //重新设置page对象
        Page<FamilyPersonVo> mapPage = new Page<>(pageNo,pageSize);
        mapPage.setRecords(familyPersionVoList);
        mapPage.setSize(familyPersonVoPage.getSize());
        mapPage.setTotal(familyPersonVoPage.getTotal());
        return mapPage;
    }

    /**
     *
     * 联谊会记录家族详情
     * @param id  主键
     * @return
     */
    @Override
    public FamilyPersonVo getFamilyPersionDetail(Integer id) {
        //根据Id查出产业详情
        FanNewsFamousPerson fanNewsFamousPerson = this.selectById(id);

        if(fanNewsFamousPerson==null){
            return null;
        }

        //查询图片
        Wrapper<FanNewsUploadFile> uploadentity = new EntityWrapper<FanNewsUploadFile>();
        uploadentity.eq("show_id", fanNewsFamousPerson.getShowId());
        uploadentity.eq("news_id",id);
        //查询所有文章id下的图片附件
        List<FanNewsUploadFile> files =  fanNewsUploadFileService.selectList(uploadentity);

        //查出名称
        AllUserLogin createUser = allUserLoginService.selectById(fanNewsFamousPerson.getCreateUser());
        AllUserLogin updateUser = allUserLoginService.selectById(fanNewsFamousPerson.getUpdateUser());

        //返回新VO的集合赋值新对象vo
        FamilyPersonVo familyPersonVo=new FamilyPersonVo();
        //调用方法封装集合
        BeanUtils.copyProperties(fanNewsFamousPerson,familyPersonVo);
        //存储图片list集合
        familyPersonVo.setFanNewsUploadFileList(files);
        //存储作者名称时间
        familyPersonVo.setUpdateTimeLong(fanNewsFamousPerson.getUpdateTime().getTime());
        familyPersonVo.setCreateTimeLong(fanNewsFamousPerson.getCreateTime().getTime());
        familyPersonVo.setCreateUserName(createUser.getRealName());
        familyPersonVo.setCreateUserName(updateUser.getRealName());
        return familyPersonVo;
    }

    /**
     * 联谊会记录家族后台新增 修改
     * @param fanNewsFamousPerson  家族名人上传实体类
     * @param fileName  上传的图片的字符串
     * @return
     */
    @Override
    public boolean addOrUpdatePersion(FanNewsFamousPerson fanNewsFamousPerson,String fileName, String filePath) {
        //生成时间
        Timestamp format = DateUtil.getCurrentTimeStamp();
        if(fanNewsFamousPerson.getId()==null){
            //存入创建时间
            fanNewsFamousPerson.setCreateTime(format);
            fanNewsFamousPerson.setCreateUser(null);
            //插入修改时间
            fanNewsFamousPerson.setUpdateTime(format);
            fanNewsFamousPerson.setUpdateUser(null);
            //默认查看数
            fanNewsFamousPerson.setVisitNum(0);
        }else{
            //存入修改时间
            fanNewsFamousPerson.setUpdateTime(format);
            fanNewsFamousPerson.setUpdateUser(null);
        }
        boolean result = this.insertOrUpdate(fanNewsFamousPerson);
        //存储图片
        if(result && StringsUtils.isNotEmpty(filePath)){
            iuploadFileService.storageFanFile(fileName,filePath,fanNewsFamousPerson.getId(),fanNewsFamousPerson.getShowId());
        }
        return result;
    }

    /**
     *删除
     * @param id
     * @param status
     * @return
     */
    @Override
    public Boolean deletePersionById(Integer id, int status) {
        FanNewsFamousPerson fanNewsFamousPerson = this.selectById(id);
        fanNewsFamousPerson.setStatus(status);
        fanNewsFamousPerson.setUpdateTime(DateUtil.getCurrentTimeStamp());
        //修改人 待写
        boolean result = this.updateAllColumnById(fanNewsFamousPerson);
        return result;
    }
}
