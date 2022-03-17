package com.lck.cmsservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lck.cmsservice.entity.CrmBanner;
import com.lck.cmsservice.entity.vo.BannerQuery;
import com.lck.cmsservice.service.CrmBannerService;
import com.lck.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author lck
 * @since 2022-03-11
 */
@RestController
@CrossOrigin //解决跨域问题
@RequestMapping("/cmsservice/bannerAdmin")
public class BannerAdminController {

    @Autowired
    private CrmBannerService crmBannerService;

    //添加banner
    @PostMapping("/addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner){
        boolean flag = crmBannerService.save(crmBanner);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //删除Banner
    @DeleteMapping("/deleteBannerById/{id}")
    public R deleteBanner(@PathVariable String id){
        boolean flag = crmBannerService.removeById(id);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //修改Banner
    @PostMapping("/updateBanner")
    public R updateBanner(@RequestBody CrmBanner crmBanner){
        boolean flag = crmBannerService.updateById(crmBanner);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }


    //根据id查询Banner
    @GetMapping("/getBannerById/{id}")
    public R getBannerById(@PathVariable String id){
        CrmBanner crmBanner = crmBannerService.getById(id);
        return R.ok().data("item", crmBanner);
    }

    //条件查询Banner
    @PostMapping("/pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable Long page, @PathVariable Long limit, @RequestBody(required = false)BannerQuery bannerQuery){
        //调用分页page对象
        Page<CrmBanner> bannerPage = new Page<>(page,limit);

        crmBannerService.pageQuery(bannerPage,bannerQuery);
        //获取数据
        List<CrmBanner> list = bannerPage.getRecords();
        //获取总记录数
        long total = bannerPage.getTotal();
        return R.ok().data("rows",list).data("total", total);
    }

}

