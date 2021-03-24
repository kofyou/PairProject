package com.example.demo.service.serviceImpl;

import com.example.demo.bean.Paper;
import com.example.demo.mapper.LimitPaperMapper;
import com.example.demo.service.LimitPaperService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: shuKai
 * @Description:
 * @Date: Create in 9:54 2021/3/24
 */
@Service
public class LimitPaperImpl implements LimitPaperService {

    @Autowired
    LimitPaperMapper limitPaperMapper;

    public List<Paper> getLimitPaper(int startPosition,int pageSize) {
        return limitPaperMapper.getLimitPaper(startPosition,pageSize);
    }

    public int getCount() {
        return limitPaperMapper.getCount();
    }
}
