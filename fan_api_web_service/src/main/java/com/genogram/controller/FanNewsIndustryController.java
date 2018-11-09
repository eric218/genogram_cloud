package com.genogram.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.genogram.config.Constants;
import com.genogram.entity.FanNewsIndustry;
import com.genogram.entityvo.FamilyIndustryVo;
import com.genogram.entityvo.NewsDetailVo;
import com.genogram.service.IFanNewsIndustryService;
import com.genogram.unit.Response;
import com.genogram.unit.ResponseUtlis;
import com.genogram.unit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 联谊会-家族产业 前端控制器
 * </p>
 *
 * @author wangwei
 * @since 2018-11-05
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/genogram/fanNewsIndustry")
public class FanNewsIndustryController {

    @Autowired
    private IFanNewsIndustryService iFanNewsIndustryService;
    /**
     * 联谊会家族产业  详情页查询
     * @Author: wang,wei
     * @Date: 2018-11-06
     * @Time: 23:02
     * @param showId 家族产业显示位置
     * @param type 种类(1:家族产业;2:个人产业)
     * @param pageNo 当前页
     * @param pageSize 每页记录数
     * @return:
     * @Description:
     *
     */
    @RequestMapping(value ="/getFamilyIndustryPage",method = RequestMethod.GET)
    public Response<FamilyIndustryVo> getFamilyCulturePage(
            @RequestParam(value = "showId") String showId,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        //判断showId是否有值
        if(StringUtils.isEmpty(showId)){
            return ResponseUtlis.error(Constants.IS_EMPTY,null);
        }
        return getFamilyIndustryVoResponse(showId, type, pageNo, pageSize);
    }

    /**
     * 联谊会家族产业 首页查询
     * @Author: wang,wei
     * @Date: 2018-11-06
     * @Time: 23:02
     * @param showId 家族产业显示位置
     * @param type 种类(1:家族产业;2:个人产业)
     * @return:
     * @Description:
     *
     */
    @RequestMapping(value ="/index/getFamilyIndexIndustryList",method = RequestMethod.GET)
    public Response<FamilyIndustryVo> getFamilyIndexIndustryList(
            @RequestParam(value = "showId") String showId,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        //判断showId是否有值
        if(StringUtils.isEmpty(showId)){
            return ResponseUtlis.error(Constants.IS_EMPTY,null);
        }
        return getFamilyIndustryVoResponse(showId, type, pageNo, pageSize);
    }

    /**
     * 抽取的公共方法
     * getFamilyIndustryVoResponse
     * @Author: wang,wei
     * @Date: 2018-11-06
     * @Time: 23:08
     * @param showId 家族产业显示位置
     * @param type 家族产业显示位置
     * @param pageNo 当前页
     * @param pageSize 每页记录数
     * @return:
     * @Description:
     *
     */
    private Response<FamilyIndustryVo> getFamilyIndustryVoResponse(String showId, String type,  Integer pageNo, Integer pageSize) {
        try {
            //状态(0:删除;1:已发布;2:草稿3:不显示)
            List statusList  = new ArrayList();
            statusList.add(1);
            //查询文章信息的条件
            Wrapper<FanNewsIndustry> entity = new EntityWrapper<FanNewsIndustry>();
            entity.eq("show_id", Integer.valueOf(showId));
            entity.in("status", statusList);
            if(StringUtils.isNotEmpty(type)){
                entity.eq("type",Integer.valueOf(type));
            }
            entity.orderBy("create_time", false);
            Page<FamilyIndustryVo> familyCultureVo = iFanNewsIndustryService.getFamilyIndustryPage(entity, pageNo, pageSize);
            if (familyCultureVo == null) {
                //没有取到参数,返回空参
                Page<FamilyIndustryVo> emptfamilyCultureVo = new Page<FamilyIndustryVo>();
                return ResponseUtlis.error(Constants.ERRO_CODE,emptfamilyCultureVo);
            }
            return ResponseUtlis.success(familyCultureVo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtlis.error(Constants.FAILURE_CODE,null);
        }
    }

    /**
     *联谊会家族产业各个产业的详情
     *@Author: yuzhou
     *@Date: 2018-11-09
     *@Time: 16:25
     *@Param:
     *@return:
     *@Description:
    */
    @RequestMapping(value ="/getFamilyIndustryDetail",method = RequestMethod.GET)
    public Response<NewsDetailVo> getFamilyIndustryDetail(
            @RequestParam(value = "id") Integer id // 家族文化详情显示位置
    ) {
        try{
            NewsDetailVo NewsDetailEmpty=new NewsDetailVo();
            if(id==null){
                return ResponseUtlis.error(Constants.ERRO_CODE,NewsDetailEmpty);
            }
            NewsDetailVo newsDetailVo= iFanNewsIndustryService.getFamilyIndustryDetail(id);
            //判断是否返回为空
            if (newsDetailVo==null){
                return ResponseUtlis.error(Constants.ERRO_CODE,NewsDetailEmpty);
            }
            return ResponseUtlis.success(newsDetailVo);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseUtlis.error(Constants.FAILURE_CODE,null);
        }
    }
}

