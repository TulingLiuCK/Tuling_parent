package com.lck.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lck.eduservice.entity.EduSubject;
import com.lck.eduservice.entity.excel.SubjectData;
import com.lck.eduservice.entity.subject.OneSubject;
import com.lck.eduservice.entity.subject.TwoSubject;
import com.lck.eduservice.listener.SubjectExcelListener;
import com.lck.eduservice.mapper.EduSubjectMapper;
import com.lck.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lck.entity.CustomerException;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author lck
 * @since 2022-03-06
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @SneakyThrows
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        //文件输入流
        try {
            InputStream is = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(is, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomerException(20002,"添加课程分类失败");
        }
    }

    //课程分类列表，树形结构
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //查询出所有的一级分类  parent_id = 0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);


        //2查询所有的二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", "0");
        List<EduSubject> twoSubjectsList = baseMapper.selectList(wrapperTwo);


        //创建集合用于封装最终数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //封装一级分类
        //查询出来所有的一级分类list集合遍历，得到每个一级分类对象，获取每个一级分类对象值
        //封装到要求的最终list集合中
        for (int i = 0; i < oneSubjectList.size(); i++) { //遍历oneSubjectList集合
            //得到oneSubjectsList中每个eduSubject对象
            EduSubject oSubject = oneSubjectList.get(i);

            //把eduSubject里面值获取出来，放到oneSubject对象中去
            OneSubject oneSubject = new OneSubject();

//            oneSubject.setId(eduSubject.getId());
//            oneSubject.setTitle(eduSubject.getTitle());
            //把eduSubject值复制到oneSubject中去【要求两个类的复制注入的属性名一致】
            BeanUtils.copyProperties(oSubject, oneSubject);

            //多个OneSubject放到finalSubjectList里面
            finalSubjectList.add(oneSubject);


            //在一级分类循环遍历查询所有的二级分类
            //创建list集合封装每个一级分类的二级分类
            ArrayList<TwoSubject> finalTwoSubjects = new ArrayList<>();

            //遍历二级list集合
            for (int j = 0; j < twoSubjectsList.size(); j++) {
                //获取每个二级分类
                EduSubject tSubject = twoSubjectsList.get(j);
                //判断二级分类parentid和一级分类id是否一样
                if (tSubject.getParentId().equals(oSubject.getId())) {
                    //把tSubject值复制到TwoSubject，最终放在twoSubjectsList中
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject, twoSubject);
                    finalTwoSubjects.add(twoSubject);
                }
            }

            //把一级下面所有二级分类放到oneSubject里面
            oneSubject.setChildren(finalTwoSubjects);
        }
        return  finalSubjectList;
    }
}
