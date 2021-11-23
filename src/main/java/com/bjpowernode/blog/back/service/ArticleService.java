package com.bjpowernode.blog.back.service;

import com.bjpowernode.blog.back.bean.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {
    List<Article> getList(String uid,String title);

    void isOpen(Article article);
}
