package com.bjpowernode.blog.back.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;

@Data
@Table(name = "t_user")
@NameStyle(Style.normal)
public class User {

    private String uid;
    private String username;
    private String nickname;
    private String password;
    private String role;
    private String createTime;
    private String lastLoginTime;
    private String state;
    private String loginIp;
    private String phone;
    private String loginCout;
    private String img;



}
