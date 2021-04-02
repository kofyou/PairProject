package entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class collection {
    //收藏论文的集合
    private HashMap<items,Integer> paperlist;

    public collection()
    {
        paperlist = new HashMap<items,Integer>();
    }
    
    
    public HashMap<items, Integer> getPaperList() {
        return paperlist;
    }

    public void setPaperList(HashMap<items, Integer> paperlist) {
        this.paperlist = paperlist;
    }

    
    /**
     * 收藏论文到收藏夹
     * @param item
     * @param number
     * @return
     */
    public boolean addPaperInCollection(items item ,int number)
    {
        if(paperlist.containsKey(item))
        {
            paperlist.put(item, paperlist.get(item)+number);
        }
        else
        {
            paperlist.put(item, number);    
        }
        return true;
    }
    
    /**
     * 从收藏夹移除论文
     * @param item
     * @return
     */
    public boolean removePaperFromCollection(items item)
    {
        paperlist.remove(item);
        return true;
    }
}
