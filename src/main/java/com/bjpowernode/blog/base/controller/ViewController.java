package com.bjpowernode.blog.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

/**
 * 负责页面的统一跳转
 */
@Controller
public class ViewController {


    //使用restFul风格
    //localhost:8080/Blog/add/zhangsan/旅游
    //                   /workbench/article/index
    @RequestMapping("/toView/{firstView}/{secondView}/{thirdView}")
    public String toView(@PathVariable("firstView") String firstView,
                         @PathVariable("secondView") String secondView,
                         @PathVariable("thirdView") String thirdView){

        //File.separator 代表分隔符，不同操作系统不一样
        return firstView + File.separator + secondView + File.separator + thirdView;
    }


}
