package yimu.controller;

import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author
 * @Program
 * @create 2022-02-27-0:26
 */
@Controller
@RequestMapping("/user")
public class UserController2 {
    // 1. 单文件上传
    @RequestMapping("/uploadFile1")
    @ResponseBody
    public void uploadFile1(String name, MultipartFile uploadFile) throws IOException {
        System.out.println(name);
        System.out.println(uploadFile);
        // 获取文件名称
        String originalFilename = uploadFile.getOriginalFilename();
        // 保存文件
        uploadFile.transferTo(new File("c:\\upload\\" + originalFilename));
    }

    // 2.多文件上传(不同文件名)
    @RequestMapping("/uploadFile2")
    @ResponseBody
    public void uploadFile2(MultipartFile uploadFile1,MultipartFile uploadFile2) throws IOException {
        // 1.获取上传文件名
        String originalFilename1 = uploadFile1.getOriginalFilename();
        String originalFilename2 = uploadFile2.getOriginalFilename();
        // 2.保存文件
        uploadFile1.transferTo(new File("c:\\upload\\" + originalFilename1));
        uploadFile2.transferTo(new File("c:\\upload\\" + originalFilename2));
    }

    // 2.多文件上传(相同文件名)：for循环
    @RequestMapping("/uploadFile3")
    @ResponseBody
    public void uploadFile3(String[] name,MultipartFile[] uploadFile) throws IOException {

        for (MultipartFile multipartFile : uploadFile) {
            System.out.println(multipartFile);
            // 1.获取上传文件名
            String originalFilename = multipartFile.getOriginalFilename();
            // 2.保存文件
            multipartFile.transferTo(new File("c:\\upload\\" + originalFilename));
        }
    }
}
