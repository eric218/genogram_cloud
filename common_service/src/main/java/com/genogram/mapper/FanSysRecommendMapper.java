package com.genogram.mapper;

import com.genogram.entity.FanSysRecommend;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.genogram.entityvo.NewsDetailVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 联谊会文章推荐表 Mapper 接口
 * </p>
 *
 * @author wangwei
 * @since 2018-11-13
 */
public interface FanSysRecommendMapper extends BaseMapper<FanSysRecommend> {

    List<NewsDetailVo> getIndexRecommend(Map map);
}
