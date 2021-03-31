package jiekou.demo.mapper;

import jiekou.demo.entity.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaperMapper {/*定义要操作数据库的动作*/
    List<Paper> findAll();

    List<Paper> searchPage(@Param("id") int id);

//    List<Paper> searchPage_(@Param("title1") String title1);
}
