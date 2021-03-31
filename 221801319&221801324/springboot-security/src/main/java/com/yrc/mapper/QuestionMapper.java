package com.yrc.mapper;


import com.yrc.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestionMapper {

    List<Question> queryQuestionList ();
    List<Question> queryQuestionByAskId (int id_ask);
    List<Question> queryQuestionByAnswerId (int id_answer);
    int deleteQuestion (int id);
    int addQuestion (Question question);
    int answerQuestion (int id ,String answer);

}
