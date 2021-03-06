package com.lck.eduservice.controller;


import com.lck.commonutils.R;
import com.lck.eduservice.entity.EduChapter;
import com.lck.eduservice.entity.chapter.ChapterVo;
import com.lck.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author lck
 * @since 2022-03-06
 */
@RestController
@RequestMapping("/eduservice/edu-chapter")
@CrossOrigin //跨域
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;


    //课程大纲列表  根据课程id进行查询
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return  R.ok().data("allChapterVideo",list);
    }

    //添加章节
    @PostMapping("/addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        chapterService.save(eduChapter);
        return R.ok();
    }
    //根据章节id查询
    @GetMapping("getChapter/{chapterId}")
    public R getChapter(@PathVariable String chapterId){
        EduChapter eduChapter = chapterService.getById(chapterId);
        return R.ok().data("chapter",eduChapter);
    }

    //修改章节
    @PostMapping("/updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);
        return R.ok();
    }
    //删除章节
    @DeleteMapping("/deleteById/{chapterId}")
    public  R deleteById(@PathVariable String chapterId){
       boolean falg =  chapterService.deleteChapter(chapterId);
       if (falg){
           return R.ok();
       }else {
           return R.error();
       }
    }

}

