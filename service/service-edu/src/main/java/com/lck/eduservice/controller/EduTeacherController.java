package com.lck.eduservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lck.commonutils.R;
import com.lck.eduservice.entity.EduTeacher;
import com.lck.eduservice.entity.vo.TeacherQuery;
import com.lck.eduservice.service.EduTeacherService;
import com.lck.entity.CustomerException;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author lck
 * @since 2022-03-03
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
@CrossOrigin // 跨域
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    //查询讲师表所有数据
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public R list() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items", list);
    }

    //逻辑删除 按照id删除
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("/deleteTeacherById/{id}")
    public R deleteTeacherById(@ApiParam(name = "id",value = "讲师ID") @PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //3分页查询讲师的方法
    @GetMapping("/pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable Long current,@PathVariable Long limit){
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        eduTeacherService.page(pageTeacher, null);
        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//总记录数
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);
    }
    //多条件查询带分页
    @ApiOperation(value = "多条件查询带分页")
    @PostMapping("/pageTeacherCondition/{page}/{limit}")
    public R pageTeacherCondition(@ApiParam(name = "page", value = "当前页码", required = true)@PathVariable Long page,
                                  @ApiParam(name = "limit", value = "每页记录数", required = true)@PathVariable Long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){ // 通过封装TeacherQuery对象来直接传递查询条件
        Page<EduTeacher> pageParam = new Page<>(page,limit);
        //调用方法实现多条件分页拆线呢
        eduTeacherService.pageQuery(pageParam,teacherQuery);
        //获取查询到的数据
        List<EduTeacher> records = pageParam.getRecords();
        //获取总记录数
        long total = pageParam.getTotal();
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);
    }
    //新增讲师
    @ApiModelProperty(value = "新增讲师")
    @PostMapping("/save")
    public R save(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.save(eduTeacher);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //根据id查询
    @ApiModelProperty(value = "根据id查询")
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable String id) throws CustomerException {
//        try {
////            int a = 10/0;
//        } catch (Exception e) {
//            throw new CustomerException(404,"运行时异常"); //自定义异常
//        }
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("item", teacher);
    }
    //修改讲师
    @ApiModelProperty(value = "修改讲师")
    @PostMapping("/updateById")
    public R updateById(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

}

