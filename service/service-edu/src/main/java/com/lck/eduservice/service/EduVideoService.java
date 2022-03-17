package com.lck.eduservice.service;

import com.lck.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author lck
 * @since 2022-03-06
 */
public interface EduVideoService extends IService<EduVideo> {

    //根据课程id 删除小节
    void removeVideoByCourseId(String id);

}
