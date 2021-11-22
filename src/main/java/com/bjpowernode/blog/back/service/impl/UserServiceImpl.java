package com.bjpowernode.blog.back.service.impl;

import com.bjpowernode.blog.back.bean.User;
import com.bjpowernode.blog.back.mapper.UserMapper;
import com.bjpowernode.blog.back.service.UserService;
import com.bjpowernode.blog.base.exception.BlogEnum;
import com.bjpowernode.blog.base.exception.BlogException;
import com.bjpowernode.blog.base.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user, String code, String rightCode) {

        //校验验证码
        if (!rightCode.equals(code)){
            throw new BlogException(BlogEnum.USER_LOGIN_CODE);
        }

        //将密码加密
        String password = user.getPassword();
        password = MD5Util.getMD5(password);
        user.setPassword(password);

        //用户名密码校验
        List<User> users = userMapper.select(user); //等值查询
        if (users.size() == 0){
            throw new BlogException(BlogEnum.USER_LOGIN_ACCOUNT);
        }

        return users.get(0);
    }

    @Override
    public void verifyOldPwd(String oldPwd, User user) {

        String password = user.getPassword();
        oldPwd = MD5Util.getMD5(oldPwd);

        if (!password.equals(oldPwd)){
            throw new BlogException(BlogEnum.USER_VERIFY_PASS);
        }

    }

    @Override
    public void updateUser(User user) {

        int count;
        if(user.getPassword()!=null){
            //用户改了密码
            //给新密码加密
            user.setPassword(MD5Util.getMD5(user.getPassword()));

            count = userMapper.updateByPrimaryKeySelective(user);
        }else {
            //用户没改密码，改的其他数据
            count = userMapper.updateByPrimaryKeySelective(user);
        }

        if (count == 0){
            throw new BlogException(BlogEnum.USER_UPDATE);
        }
    }
}
