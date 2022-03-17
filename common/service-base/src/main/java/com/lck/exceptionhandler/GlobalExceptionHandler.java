package com.lck.exceptionhandler;

import com.lck.commonutils.R;
import com.lck.entity.CustomerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 #Create by LCK on 2022/3/4
 # 用法: 统一处理异常
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends Throwable {


    //指定出现什么异常才会执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody  //因为他不在Controller中，所以数据不会返回，所以添加@ResponseBody返回数据
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了自定异常");
    }

    @ExceptionHandler(CustomerException.class)
    @ResponseBody
    public R error(CustomerException e){
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}
