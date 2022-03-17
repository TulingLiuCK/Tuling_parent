package com.lck.cmsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lck.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lck.cmsservice.entity.vo.BannerQuery;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author lck
 * @since 2022-03-11
 */
public interface CrmBannerService extends IService<CrmBanner> {

    void pageQuery(Page<CrmBanner> bannerPage, BannerQuery bannerQuery);

    List<CrmBanner> getAllBanner();
}
