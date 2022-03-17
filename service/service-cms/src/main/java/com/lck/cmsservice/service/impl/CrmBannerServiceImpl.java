package com.lck.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lck.cmsservice.entity.CrmBanner;
import com.lck.cmsservice.entity.vo.BannerQuery;
import com.lck.cmsservice.mapper.CrmBannerMapper;
import com.lck.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author lck
 * @since 2022-03-11
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public void pageQuery(Page<CrmBanner> bannerPage, BannerQuery bannerQuery) {
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        String name = bannerQuery.getName();
        String begin = bannerQuery.getBegin();
        String end = bannerQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("title", name);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_modified", end);
        }
        //排序
        queryWrapper.orderByDesc("gmt_create");

        //带上分页判断后的条件进行查询
        baseMapper.selectPage(bannerPage,queryWrapper);
    }

    @Override
    @Cacheable(key = "'selectIndexList'",value = "banner") //根据方法对其返回结果进行缓存，下次请求时，如果缓存存在，则直接读取缓存数据返回，如果不存在，执行方法， 并把返回的记过放入到缓存中，一般用在查询方法
    public List<CrmBanner> getAllBanner() {
        //根据id进行降序排序
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 2");

        List<CrmBanner> list = baseMapper.selectList(wrapper);
        return list;
    }
}
