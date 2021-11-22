package com.bjpowernode.blog.back.service;

import com.bjpowernode.blog.back.bean.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User login(User user, String code, String rightCode);

    void verifyOldPwd(String oldPwd, User user);

    void updateUser(User user);
}
