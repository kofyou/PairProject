package cn.itcast.dao;

import cn.itcast.domain.PaperInfo;

import java.util.List;

public interface PaperInfoDAO {
    List<PaperInfo> selectPaperInfos();
    void add(PaperInfo paperInfo);
    void delete(PaperInfo paperInfo);
    PaperInfo getinfo(String title);

}
