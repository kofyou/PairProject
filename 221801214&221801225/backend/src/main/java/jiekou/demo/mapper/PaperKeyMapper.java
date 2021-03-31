package jiekou.demo.mapper;

import jiekou.demo.entity.Paper;
import jiekou.demo.entity.Paperkey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaperKeyMapper {
    List<Paperkey> findAll();
}
