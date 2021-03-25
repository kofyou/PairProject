package com.example.demo.mapper;

import com.example.demo.bean.Paper;
import org.apache.ibatis.annotations.*;

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

    @Select("SELECT COUNT(*) FROM paper")
    public Integer getCount();

    @Select("SELECT * FROM paper WHERE keywords like concat('%', #{value}, '%')" +
            "or publicationTitle like concat('%', #{value}, '%') limit #{startPosition} ,#{pageSize}")
    public List<Paper> searchByKeyWords(@Param("value") String keyword,@Param("startPosition") int startPosition, @Param("pageSize") int pageSize);

    @Select("SELECT COUNT(*) FROM paper WHERE keywords like concat('%', #{value}, '%') or publicationTitle like concat('%', #{value}, '%')")
    public Integer getCuntS(@Param("value") String keyword);

    @Delete("DELETE FROM paper WHERE paperId = #{id}")
    public Integer deletePaper(@Param("id") int paperId);

    @Update("UPDATE paper SET keywords = #{keywords},abstrac = #{abstrac},publicationTitle = #{publicationTitle}," +
            "publicationYear = #{publicationYear}, persistentLink = #{persistentLink} where paperId = #{paperId}")
    public Integer updatePaper(@Param("paperId") int paperId,@Param("keywords") String keywords,
                               @Param("abstrac") String abstrac,@Param("publicationTitle") String publicationTitle,
                               @Param("publicationYear") String publicationYear,@Param("persistentLink") String persistentLink);

}
