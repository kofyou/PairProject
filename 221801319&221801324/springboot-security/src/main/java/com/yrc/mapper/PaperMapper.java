package com.yrc.mapper;


import com.yrc.pojo.paper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PaperMapper {
    List<paper> querypaperList();
    List<paper> search_by_gjc (String gjc);
    void delete_paper (int id);
}
