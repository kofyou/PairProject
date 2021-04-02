package com.example.demo.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: json文件循环读取工具类
 * @Author: 曹鑫
 * @Date: 2021/3/31
 */
public class FileUtil {
    //获取目录下的所有文件。
    public static List<File> getFiles(String path) {
        File root = new File(path);
        List<File> files = new ArrayList<File>();
        if (!root.isDirectory()) {
            files.add(root);
        } else {
            File[] subFiles = root.listFiles();
            for (File f : subFiles) {
                files.addAll(getFiles(f.getAbsolutePath()));
            }
        }
        return files;
    }
}
