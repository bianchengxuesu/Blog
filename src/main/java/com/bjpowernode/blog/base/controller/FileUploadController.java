package com.bjpowernode.blog.base.controller;

import com.bjpowernode.blog.back.bean.User;
import com.bjpowernode.blog.base.util.DateTimeUtil;
import com.bjpowernode.blog.base.util.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FileUploadController {

    //解决Editormd文件上传
    @RequestMapping("/editorUpload")
    @ResponseBody
    public Map<String,Object> editorUpload(
            @RequestParam(value = "editormd-image-file",required = false) MultipartFile img,
            HttpSession session){

        Map<String,Object> map = FileUploadUtil.fileUpload(img,session);

        return map;
    }


    //这儿不能加@RequestParam
    @RequestMapping("/fileUpload")
    @ResponseBody
    public Map<String,Object> fileUpload(MultipartFile img, HttpSession session){

        Map<String,Object> map = FileUploadUtil.fileUpload(img,session);

        return map;
    }



}
