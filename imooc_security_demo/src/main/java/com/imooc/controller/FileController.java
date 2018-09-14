package com.imooc.controller;


import com.imooc.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * @Auther: cmy
 * @Date: 2018/7/5 17:16
 * @Description:
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private  String folder="F:\\imooc_security\\imooc_security_demo\\src\\main\\java\\com\\imooc\\dto";
    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());//传过来的参数的名字
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile = new File(folder, new Date().getTime() + ".txt");
        file.transferTo(localFile);//把传过来的文件写到本地的文件
        System.out.println(localFile.getAbsolutePath());
        return  new FileInfo(localFile.getAbsolutePath());
    }
    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response){
        try( InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
            OutputStream outputStream=response.getOutputStream();) {
            response.setContentType("application/x-down");
            response.addHeader("Content-Disposition","attachment;filename=test.txt");
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
