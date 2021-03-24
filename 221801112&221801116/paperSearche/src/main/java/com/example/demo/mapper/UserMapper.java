package com.example.demo.mapper;

import com.example.demo.bean.Paper;
import com.example.demo.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper
{
    @Select("SELECT * FROM USER WHERE NAME='曹鑫'")
    public User selUserByName(String name);

    @Select("SELECT * FROM paper")
    public List<Paper> selAllPaper();
}
