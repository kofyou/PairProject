package com.example.demo.service.serviceImpl;

import com.example.demo.bean.Paper;
import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IndexSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl implements IndexSerice
{
    @Autowired
    UserMapper userMapper;

    @Override
    public User login(User user)
    {
        System.out.println("进入service层:"+user.getName());
        User  tmp=userMapper.selUserByName(user.getName());
        System.out.println("获得的USER："+tmp.getName());
        if (user.getPassword()==null||!(user.getPassword().equals(tmp.getPassword())))
            return null;
        return tmp;
    }

}
