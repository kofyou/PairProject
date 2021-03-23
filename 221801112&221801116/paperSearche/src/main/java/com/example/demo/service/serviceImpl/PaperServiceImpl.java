package com.example.demo.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.bean.Paper;
import com.example.demo.mapper.PaperMapper;
import com.example.demo.service.PaperService;
import org.springframework.stereotype.Service;

/**
 * @Author: shuKai
 * @Description:
 * @Date: Create in 13:12 2021/3/23
 */
@Service("paperService")
public class PaperServiceImpl extends ServiceImpl<PaperMapper,Paper> implements PaperService{

}
