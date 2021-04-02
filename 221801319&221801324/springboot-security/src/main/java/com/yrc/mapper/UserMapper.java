package com.yrc.mapper;

import com.yrc.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper //表示这是一个mybatis的mapper类
public interface UserMapper {

    List<User> queryUserList ();
    User queryUserByName (String name);
    User queryUserById (int id);
    User queryUserByEmail (String email);
    int addUser (User user);
    int updateUser (User user);
    int deleteUser (int id);
    int updateEmail_statue (String name);
    int updateBox_statue (String name);
    int close_box (String name);
    int updateName (String name ,int id);
    int updatePassword (String password,int id);
    int updateEmail (String email,int id);
    int tip_off (int id);
    int remove_tip_off (int id);
    int add_ask (int id);
    int delete_ask (int id);
    int add_answer (int id);
    int add_asked (int id);
    int update_head (int id, String filePath);//跟新头像

}
