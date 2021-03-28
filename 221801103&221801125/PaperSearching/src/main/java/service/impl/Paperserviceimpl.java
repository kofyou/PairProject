package service.impl;

import dao.PaperDaoimpl;
import pojo.Paper;

import java.util.List;

public class Paperserviceimpl
{
    PaperDaoimpl paperDaoimpl=new PaperDaoimpl();

    public Paper GetAPaper(String title)
    {
        return paperDaoimpl.GetAPaper(title);
    }

    public List<Paper> GetPaperList(String[] searchwords,int type)
    {
        return paperDaoimpl.GetPaticularPapers(searchwords,type);
    }
}
