package service.impl;

import dao.PaperDaoimpl;
import pojo.Paper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paperserviceimpl
{
    PaperDaoimpl paperDaoimpl=new PaperDaoimpl();

    public Paper GetAPaper(String title)
    {
        return paperDaoimpl.GetAPaper(title);
    }

    public List<Paper> GetPaperList(String searchwords,int type)
    {
        return paperDaoimpl.GetPaticularPapers(searchwords,type);
    }

    public HashMap<String,Integer> GetHottestKeywords()
    {
        return paperDaoimpl.GetHottestKeywords();
    }

    public Boolean IsCollected(String username,String title)
    {
        return paperDaoimpl.IsCollected(username,title);
    }

    public List<Paper> GetMyCollect(String username)
    {
        return paperDaoimpl.GetMyCollect(username);
    }
    public void UpdateMyCollect(String account,String title)
    {
        paperDaoimpl.UpdateMyCollect(account, title);
    }

    public void DeleteMyCollect(String account,String title)
    {
        paperDaoimpl.DeleteMyCollect(account,title);
    }

    public HashMap<String,Integer> GetConferenceCount()
    {
        return paperDaoimpl.GetConferencesCount();
    }
}
