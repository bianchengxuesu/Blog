package com.bjpowernode.blog.base.bean;

import com.bjpowernode.blog.back.bean.Article;
import lombok.Data;


public class ResultVo {

    private String mess; //返回客户端的信息
    private boolean isOk; //用户是否操作成功
    private Article t;

    public Article getT() {
        return t;
    }

    public void setT(Article t) {
        this.t = t;
    }

    public ResultVo(){}

    public ResultVo(String mess, boolean isOk,Article t) {
        this.mess = mess;
        this.isOk = isOk;
        this.t = t;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }
}
