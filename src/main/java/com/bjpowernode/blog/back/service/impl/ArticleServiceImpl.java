package com.bjpowernode.blog.back.service.impl;

import com.bjpowernode.blog.back.bean.Article;
import com.bjpowernode.blog.back.bean.Category;
import com.bjpowernode.blog.back.mapper.ArticleMapper;
import com.bjpowernode.blog.back.mapper.CategoryMapper;
import com.bjpowernode.blog.back.service.ArticleService;
import com.bjpowernode.blog.base.exception.BlogEnum;
import com.bjpowernode.blog.base.exception.BlogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public List<Article> getList(String uid,String title) {

        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        //查询当前登录用户的文章
        criteria.andEqualTo("uid",uid);

        //title不为空就增加criteria标准，为空就不加，不影响最后结果
        if(title != null && !title.equals("")){
            //title不为空
            criteria.andLike("title","%"+title+"%");
        }

        List<Article> articles = articleMapper.selectByExample(example);
        //遍历文章列表，根据cid查询栏目表，替换字段
        for (Article article : articles) {

            String cid = article.getCid();

            Category category = categoryMapper.selectByPrimaryKey(cid);

            article.setCid(category.getCname());

        }
        return articles;
    }

    @Override
    public void isOpen(Article article) {

        int count = articleMapper.updateByPrimaryKeySelective(article);

        if (count == 0){

            throw new BlogException(BlogEnum.ARTICLE_ISOPEN);

        }

    }
}
