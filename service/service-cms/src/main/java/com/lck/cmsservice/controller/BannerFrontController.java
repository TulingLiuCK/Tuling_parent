package com.lck.cmsservice.controller;

import com.lck.cmsservice.entity.CrmBanner;
import com.lck.cmsservice.service.CrmBannerService;
import com.lck.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.Cacheable;

import java.util.List;

/***
 #Create by LCK on 2022/3/12
 # 用法:首页获取banner数据接口
 */
@CrossOrigin //跨越
@RestController
@RequestMapping("/cmsservice/bannerFront")
public class BannerFrontController {

    @Autowired
    private CrmBannerService crmBannerService;

    //获取所有Banner

    @GetMapping("getAll")
    public R getAll(){
        List<CrmBanner> list = crmBannerService.getAllBanner();
        return R.ok().data("list", list);
    }
}
