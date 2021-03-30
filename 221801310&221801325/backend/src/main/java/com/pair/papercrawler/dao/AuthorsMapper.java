package com.pair.papercrawler.dao;

import com.pair.papercrawler.models.Paper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthorsMapper {
//    List<Integer> selectByName(String author);
    /***
    * @Description:  根据作者名， 返回该作者参与的论文列表
    * @Param:   author:String
    * @return: List<Paper>
    * @Author: top
    * @Date: 2021/3/30
    */
    List<Paper> selectPaperByAuthorName(String author);
    /***
     * @Description:  根据论文id，返回该论文的作者的String数组
     * @Param: paperId
     * @return:  String[]
     * @Author: top
     * @Date: 2021/3/30
     */
    String[] selectAuthorByPaperId(Integer paperId);

}
