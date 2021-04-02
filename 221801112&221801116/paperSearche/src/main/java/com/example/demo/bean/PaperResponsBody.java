package com.example.demo.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 自定义响应体。
 * @Author: 曹鑫
 * @Date: 2021/3/31
 */
public class PaperResponsBody {
    String code;
    String msg;
    Integer count;
    List<Paper> data = new ArrayList<>();

    @Override
    public String toString() {
        return "PaperResponsBody{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Paper> getData() {
        return data;
    }

    public void setData(List<Paper> data) {
        this.data = data;
    }
}
