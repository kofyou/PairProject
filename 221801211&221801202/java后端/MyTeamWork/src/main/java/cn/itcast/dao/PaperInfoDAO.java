package cn.itcast.dao;

import cn.itcast.domain.PaperInfo;

import java.util.List;

public interface PaperInfoDAO {
    List<PaperInfo> selectPaperInfos();
    void add(PaperInfo paperInfo);
    void delete(String title);
    List<PaperInfo> getinfo(String title);
    List<PaperInfo> getkey(String title);
}
