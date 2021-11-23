package com.bjpowernode.blog.base.controller;

import com.bjpowernode.blog.back.bean.User;
import com.bjpowernode.blog.base.util.DateTimeUtil;
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

        String realPath = session.getServletContext().getRealPath("/upload");
        realPath += File.separator + DateTimeUtil.getDate();
        //获取登录用户
        User user = (User) session.getAttribute("user");
        realPath += File.separator + user.getUsername();

        File file = new File(realPath);
        if(!file.exists()){
            //创建带层级的目录 mkdir:只能创建一级目录
            file.mkdirs();
        }
        //相同用户可能会上传相同的文件名的图片，防止文件重名
        //获取用户名
        String fileName = img.getOriginalFilename();

        //毫秒值文件名.png/jpg
        fileName = System.currentTimeMillis() + fileName;

        //定义用于给Editormd返回的map数据
        Map<String,Object> map = new HashMap<>();
        //获取回调地址
        String url = "http//:localhost:8080/Blog/upload/"+DateTimeUtil.getDate()
                + File.separator + user.getUsername() + File.separator + fileName;
        try {
            img.transferTo(new File(realPath + File.separator + fileName));
            map.put("success",1);
            map.put("url",url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}
