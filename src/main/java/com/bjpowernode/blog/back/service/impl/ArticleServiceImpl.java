package com.bjpowernode.blog.back.service.impl;

import com.bjpowernode.blog.back.bean.Article;
import com.bjpowernode.blog.back.bean.Category;
import com.bjpowernode.blog.back.bean.Tag;
import com.bjpowernode.blog.back.mapper.ArticleMapper;
import com.bjpowernode.blog.back.mapper.CategoryMapper;
import com.bjpowernode.blog.back.mapper.TagMapper;
import com.bjpowernode.blog.back.service.ArticleService;
import com.bjpowernode.blog.base.exception.BlogEnum;
import com.bjpowernode.blog.base.exception.BlogException;
import com.bjpowernode.blog.base.util.DateTimeUtil;
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

    @Autowired
    private TagMapper tagMapper;


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

    @Override
    public List<Category> queryCategory() {

        List<Category> list = categoryMapper.selectAll();

        return list;

    }

    @Override
    public List<Tag> queryTags(String cid) {
        Tag tag = new Tag();
        tag.setCid(cid);

        return tagMapper.select(tag);
    }

    @Override
    public Article saveOrUpdate(Article article) {

        if (article.getAid() == null){
            //aid为null，说明是添加
            //点赞数开始为零
            article.setThumbsUp("0");
            //是否热门为零
            article.setIs_hot("0");
            //访问量为零
            article.setVisit_count("0");
            //发布时间
            article.setCreate_time(DateTimeUtil.getSysTime());
            //是否被评论
            article.setIsCommented("0");

            int count = articleMapper.insertSelective(article);
            if (count == 0){
                //发布失败
                throw new BlogException(BlogEnum.ARTICLE_PUNISH);
            }

        }else {

            article.setUpdate_time(DateTimeUtil.getSysTime());
            int count = articleMapper.updateByPrimaryKeySelective(article);
            if (count == 0){
                //修改失败
                throw new BlogException(BlogEnum.ARTICLE_UPDATE);
            }

        }

        return article;
    }

    @Override
    public Article queryById(String id) {

        Article article = articleMapper.selectByPrimaryKey(id);

        return article;
    }

    @Override
    public void deleteById(String id) {
        int count = articleMapper.deleteByPrimaryKey(id);
        if (count == 0){
            throw new BlogException(BlogEnum.ARTICLE_DELETE);
        }
    }
}
