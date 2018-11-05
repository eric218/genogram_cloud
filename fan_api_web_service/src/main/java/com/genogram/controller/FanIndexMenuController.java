package com.genogram.controller;

import com.genogram.entityvo.FanSysWebMenuVo;
import com.genogram.entityvo.TestVo;
import com.genogram.service.IFanSysWebNewsShowService;
import com.genogram.unit.Response;
import com.genogram.unit.ResponseUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 联谊会首页-设置 前端控制器
 * </p>
 *
 * @author wangwei
 * @since 2018-11-05
 */
@Controller
@RequestMapping("/genogram/fanMenu")
@CrossOrigin(origins = "*")
public class FanIndexMenuController {
    @Autowired
    private IFanSysWebNewsShowService iFanSysWebNewsShowService;

    @ResponseBody
    @RequestMapping(value = "/getMenuBySiteId" ,  method = RequestMethod.GET)
    public Response<TestVo> test1(@RequestParam(name = "siteId") String siteId){
        //单表查询list
        List<FanSysWebMenuVo> list = iFanSysWebNewsShowService.getMenu(siteId);
        return ResponseUtlis.success(list);
    }
}

