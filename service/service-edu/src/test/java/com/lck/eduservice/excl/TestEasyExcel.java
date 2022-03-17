package com.lck.eduservice.excl;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/***
 #Create by LCK on 2022/3/6
 # 用法:
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写的操作
        //设置写入文件夹的地址和excel文件夹的名称
        String fileName = "D:\\write.xlsx";
        //调用easyexcel里面的方法实现写操作
        //write两个参数，第一个参数文件路径名称，第二个参数实体类
//        EasyExcel.write(fileName, DemoData.class).sheet("学生列表").doWrite(getData());

        //读操作
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();
    }
    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy"+i);
            list.add(data);
        }
        return list;
    }
}
