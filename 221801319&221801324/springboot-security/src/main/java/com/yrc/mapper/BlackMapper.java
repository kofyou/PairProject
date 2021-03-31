package com.yrc.mapper;

import com.yrc.pojo.Black;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlackMapper {

    int addBlack (int id1,int id2); //id1拉黑id2
    int deleteBlack (int id1,int id2); //解除拉黑
    List<Black> queryBlack ();
}
