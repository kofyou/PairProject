package com.example.demo.mapper;

import com.example.demo.bean.Paper;
import com.example.demo.bean.PaperAnslyse;
import com.example.demo.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper
{
    @Select("SELECT * FROM USER WHERE NAME='曹鑫'")
    public User selUserByName(String name);

    @Insert("INSERT INTO paper VALUES(DEFAULT,#{authors},#{keywords},#{abstrac},#{publicationTitle},#{publicationYear},#{persistentLink},#{conference})")
    public int insPaper(Paper paper);

    @Select("SELECT * FROM paper")
    public List<Paper> selAllPaper();

    @Select("SELECT keywords,publicationYear FROM paper WHERE conference=#{conference}")
    public List<Paper> selPaperByConference(Paper paper);

    @Insert("INSERT INTO paperAnslyse VALUES(DEFAULT,#{dataStaticData},#{paperAnslyseData},#{dataWordsCloud},null,#{conference});")
    public int insPaperAnslyse(PaperAnslyse paperAnslyse);

    @Select("SELECT * FROM paperanslyse WHERE conference=#{conference}")
    public PaperAnslyse selPaperAnslyseByPaId(PaperAnslyse paperAnslyse);
}