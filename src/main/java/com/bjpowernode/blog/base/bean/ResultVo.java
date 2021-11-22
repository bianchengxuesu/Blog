package com.bjpowernode.blog.base.bean;

import lombok.Data;


public class ResultVo {

    private String mess; //返回客户端的信息
    private boolean isOk; //用户是否操作成功

    public ResultVo(){}

    public ResultVo(String mess, boolean isOk) {
        this.mess = mess;
        this.isOk = isOk;
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
