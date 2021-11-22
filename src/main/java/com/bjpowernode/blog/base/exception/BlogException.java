package com.bjpowernode.blog.base.exception;

public class BlogException extends RuntimeException{

    private BlogEnum blogEnum;

    public BlogException(BlogEnum blogEnum){

        //从异常堆栈中获取异常信息的话
        super(blogEnum.getMessage());
        this.blogEnum = blogEnum;
    }

}
