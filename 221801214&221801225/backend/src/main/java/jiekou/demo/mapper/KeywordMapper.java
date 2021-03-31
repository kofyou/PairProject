package jiekou.demo.mapper;

import jiekou.demo.entity.Keyword;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KeywordMapper {
    List<Keyword> findTopten();
}