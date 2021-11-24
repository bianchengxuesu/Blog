package com.bjpowernode.blog.back.controller;

import com.bjpowernode.blog.back.bean.Article;
import com.bjpowernode.blog.back.bean.Category;
import com.bjpowernode.blog.back.bean.Tag;
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


    //查询种类，如：运动、生活、编程等
    @ResponseBody
    @RequestMapping("/article/queryCategory")
    public List<Category> queryCategory(){

        List<Category> categories = articleService.queryCategory();

        return categories;
    }

    //查询种类下对应的标签，如：运动下有户外运动、赛艇、羽毛球、垒球等
    @ResponseBody
    @RequestMapping("/article/queryTags")
    public List<Tag> queryTags(String cid){

        List<Tag> tags = articleService.queryTags(cid);

        return tags;
    }

    //异步发布和更新文章
    @ResponseBody
    @RequestMapping("/article/saveOrUpdate")
    public ResultVo saveOrUpdate(Article article,HttpSession session){

        ResultVo resultVo = new ResultVo();

        try {
            //获取登录用户
            User user = (User) session.getAttribute("user");
            article.setUid(user.getUid());
            article = articleService.saveOrUpdate(article);
            resultVo.setOk(true);
            resultVo.setT(article);
            if (article.getAid() == null) {
                resultVo.setMess("发布文章成功");
            }else {
                resultVo.setMess("修改文章成功");
            }
        }catch (Exception e){
            resultVo.setMess(e.getMessage());
        }

        return resultVo;
    }

    //异步查询文章信息
    @ResponseBody
    @RequestMapping("/article/queryById")
    public Article queryById(String id){
        System.out.println(id);
        Article article = articleService.queryById(id);
        return article;
    }

    //异步删除文章信息
    @ResponseBody
    @RequestMapping("/article/deleteById")
    public ResultVo deleteById(String id){
        ResultVo resultVo = new ResultVo();
        try {
            articleService.deleteById(id);
            resultVo.setOk(true);
            resultVo.setMess("删除文章成功");
        }catch (Exception e){
            resultVo.setMess(e.getMessage());
        }

        return resultVo;
    }

}
