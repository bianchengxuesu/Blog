package com.bjpowernode.blog.back.service;

import com.bjpowernode.blog.back.bean.Article;
import com.bjpowernode.blog.back.bean.Category;
import com.bjpowernode.blog.back.bean.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {
    List<Article> getList(String uid,String title);

    void isOpen(Article article);

    List<Category> queryCategory();

    List<Tag> queryTags(String cid);

    Article saveOrUpdate(Article article);

    Article queryById(String id);

    void deleteById(String id);
}
