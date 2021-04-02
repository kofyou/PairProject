package com.yrc.mapper;

import com.yrc.pojo.Admin;
import com.yrc.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper

public interface AdminMapper {
    List<Admin> queryAdminList ();
    Admin queryAdminByIName (String name);
    int addAdmin (User user);
    int updateAdmin (User user);
    int deleteAdmin (int id);


}
