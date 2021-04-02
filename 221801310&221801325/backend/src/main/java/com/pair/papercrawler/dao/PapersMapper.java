package com.pair.papercrawler.dao;

import com.github.pagehelper.Page;
import com.pair.papercrawler.models.Paper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PapersMapper {
    
    /*** 
    * @Description: 根据paperId删除论文
    * @Param:  
    * @return:  
    * @Author: top
    * @Date: 2021/3/30 
    */
    void deletePaperByPaperId(Integer paperId);

    /*** 
    * @Description: 根据标题搜索论文 
    * @Param:  
    * @return:  
    * @Author: top
    * @Date: 2021/3/30
    */
    List<Paper> selectPaperByTitle(String title);

    /*** 
    * @Description: 返回相关论坛的论文
    * @Param:  CVPR ICCV  ECCV
    * @return:  
    * @Author: top
    * @Date: 2021/3/30 
    */
    List<Paper> selectPaperByMagazine(String magazine);


    /***
    * @Description: 根据paperId返回Paper
    * @Param:
    * @return:
    * @Author: top
    * @Date: 2021/3/30
    */
    Paper selectPaperByPaperId(Integer paperId);

    /*** 
    * @Description: 返回所有的论文 
    * @Param:  
    * @return:  
    * @Author: top
    * @Date: 2021/3/30 
    */
    List<Paper> selectAll();


    List<Paper> selectPaperByAbstracts(String abstracts);


}
