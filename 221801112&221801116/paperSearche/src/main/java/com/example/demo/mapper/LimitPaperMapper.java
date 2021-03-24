package com.example.demo.mapper;

import com.example.demo.bean.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: shuKai
 * @Description:
 * @Date: Create in 9:52 2021/3/24
 */
@Mapper
public interface LimitPaperMapper {

    @Select("SELECT * FROM paper limit #{startPosition} ,#{pageSize}")
    public List<Paper> getLimitPaper(@Param("startPosition") int startPosition, @Param("pageSize") int pageSize);

    @Select("SELECT COUNT(*) FROM PAPER")
    public int getCount();

    @Select("SELECT * FROM PAPER WHERE keywords like '%${value}%' limit #{startPosition} ,#{pageSize}")
    public List<Paper> searchByKeyWords(@Param("value") String keyword,@Param("startPosition") int startPosition, @Param("pageSize") int pageSize);

    @Select("SELECT COUNT(*) FROM PAPER WHERE keywords like '%${value}%'")
    public int getCuntS(@Param("value") String keyword);
}
