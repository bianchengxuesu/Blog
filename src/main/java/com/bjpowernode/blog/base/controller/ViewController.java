package com.bjpowernode.blog.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Enumeration;

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
                         @PathVariable("thirdView") String thirdView,
                         HttpServletRequest request){

        //获取前台所有参数名字
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            String value = request.getParameter(name);
            //设置到request域中
            request.setAttribute(name,value);
        }

        //File.separator 代表分隔符，不同操作系统不一样
        return firstView + File.separator + secondView + File.separator + thirdView;
    }


}
