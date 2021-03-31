package jiekou.demo.mapper;

import jiekou.demo.entity.Keyword;
import jiekou.demo.entity.Magazine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MagazineMapper {
    List<Magazine> findmagazine();
}
