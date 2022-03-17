package com.lck.eduservice.service;

import com.lck.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lck.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lck
 * @since 2022-03-06
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String id);

}
