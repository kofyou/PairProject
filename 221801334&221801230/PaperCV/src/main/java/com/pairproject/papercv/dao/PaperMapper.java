package com.pairproject.papercv.dao;

import com.pairproject.papercv.model.Paper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 论文mapper
 *
 * @date 2021/03/23
 */
@Mapper
public interface PaperMapper {

    /**
     * 查询所有论文
     *
     * @return
     */
    List<Paper> selectAll();

    /**
     * 通过论文标题查询论文（模糊查询）
     *
     * @param title 论文标题
     * @return 论文列表
     */
    List<Paper> selectByTitle(String title);

    /**
     * 通过会议查询论文
     *
     * @param meeting
     * @return
     */
    List<Paper> selectByMeeting(String meeting);

    /**
     * 获取论文数量
     *
     * @param meeting 会议名称
     * @return
     */
    Integer readCount(String meeting);

    /**
     * 插入论文
     *
     * @param paper 论文
     */
    void insert(Paper paper);

}
