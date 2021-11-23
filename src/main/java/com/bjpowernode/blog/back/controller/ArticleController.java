package com.bjpowernode.blog.back.controller;

import com.bjpowernode.blog.back.bean.Article;
import com.bjpowernode.blog.back.bean.User;
import com.bjpowernode.blog.back.service.ArticleService;
import com.bjpowernode.blog.back.service.UserService;
import com.bjpowernode.blog.base.bean.ResultVo;
import com.bjpowernode.blog.base.exception.BlogException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @RequestMapping("/article/list")
    public PageInfo<Article> list(int page, int pageSize,String title,HttpSession session){

        //获取当前登录用户
        User user = (User) session.getAttribute("user");

        String uid = user.getUid();

        PageHelper.startPage(page,pageSize);
        List<Article> articles = articleService.getList(uid,title);

        PageInfo<Article> pageInfo = new PageInfo<>(articles);

        return pageInfo;
    }


    //修改文章是否公开
    @ResponseBody
    @RequestMapping("/article/isOpen")
    public ResultVo isOpen(Article article){

        ResultVo resultVo = new ResultVo();

        try {
            articleService.isOpen(article);
            resultVo.setOk(true);
            //公开1，私密0
            if (article.getIsOpen().equals("0")){
                resultVo.setMess("文章已私密");
            }else {
                resultVo.setMess("文章已公开");
            }
        }catch (Exception e){
            resultVo.setMess(e.getMessage());
        }


        return resultVo;
    }



}
