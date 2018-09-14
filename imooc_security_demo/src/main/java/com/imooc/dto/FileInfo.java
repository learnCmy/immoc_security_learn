package com.imooc.dto;

/**
 * @Auther: cmy
 * @Date: 2018/7/5 17:17
 * @Description:
 */
public class FileInfo {
    public FileInfo(){

    }

    public FileInfo(String path) {
        this.path = path;
    }

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
