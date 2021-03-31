package com.yrc.mapper;


import com.yrc.pojo.Tip_off;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Tip_offMapper {
    int addTip_off (int id,String reason);
    List<Tip_off> queryTipList ();
}
