package com.pair.papercrawler.dao;

import com.pair.papercrawler.models.Paper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KeywordsMapper {
    /***
     * @Description: 根据keyword返回相关的论文 keyword需要模糊查询？
     * @Param:
     * @return:
     * @Author: top
     * @Date: 2021/3/30
     */
    List<Paper> selectPaperByKeyword(String keyword);

    /***
     * @Description:
     * @Param:
     * @return:
     * @Author: top
     * @Date: 2021/3/30
     */
    List<String> selectKeywordByPaperId(Integer paperId);
}