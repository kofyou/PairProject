package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.DBHelper;
import util.WordCountMethod;
import entity.items;

//论文的业务逻辑类
public class ItemsDao {

    /**
     * 获取所有的论文信息
     * @return
     */
    public ArrayList<items> getAllItems() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<items> list = new ArrayList<items>(); // 论文集合
        try {
            conn = DBHelper.getConnection();
            String sql = "select * from paperslist;"; // SQL语句
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                items item = new items();
                item.setId(rs.getString("id"));
                item.setAbstracts(rs.getString("abstracts"));
                item.setConference(rs.getString("conference"));
                item.setKeyword(rs.getString("keyword"));
                item.setTime(rs.getString("time"));
                item.setTitle(rs.getString("title"));
                item.setLink(rs.getString("link"));
                list.add(item);// 把一个论文加入集合
            }
            return list; // 返回集合。
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            // 释放数据集对象
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            // 释放语句对象
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    /**
     * 根据论文Id获取论文信息
     * @param id
     * @return
     */
    public items getItemsById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String idStr = Integer.toString(id);
        try {
            conn = DBHelper.getConnection();
            String sql = "select * from paperslist where id=?;"; // SQL语句
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idStr);
            rs = stmt.executeQuery();
            if (rs.next()) {
                items item = new items();
                item.setId(rs.getString("id"));
                item.setAbstracts(rs.getString("abstracts"));
                item.setConference(rs.getString("conference"));
                item.setKeyword(rs.getString("keyword"));
                item.setTime(rs.getString("time"));
                item.setTitle(rs.getString("title"));
                item.setLink(rs.getString("link"));
                return item;
            } else {
                return null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            // 释放数据集对象
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            // 释放语句对象
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
    
    /**
     * 根据论文标题检索论文
     * @param title
     * @return
     */
    public ArrayList<items> getItemsByTitle(String title) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<items> list = new ArrayList<items>(); // 论文集合
        try {
            conn = DBHelper.getConnection();
            String sql = "select * from paperslist where title like ?;"; // SQL语句
            stmt = conn.prepareStatement(sql);
            String condition = "%" + title + "%";
            stmt.setString(1, condition);
            rs = stmt.executeQuery();
            while (rs.next()) {
                items item = new items();
                item.setId(rs.getString("id"));
                item.setAbstracts(rs.getString("abstracts"));
                item.setConference(rs.getString("conference"));
                item.setKeyword(rs.getString("keyword"));
                item.setTime(rs.getString("time"));
                item.setTitle(rs.getString("title"));
                item.setLink(rs.getString("link"));
                list.add(item);// 把一个论文加入集合
                
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            // 释放数据集对象
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            // 释放语句对象
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
    
    /**
     * 从数据库取出所有论文关键词
     * @return
     */
    public String getKeywordsFromDB() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<items> list = new ArrayList<items>(); // 论文集合
        try {
            conn = DBHelper.getConnection();
            String sql = "select keyword from paperslist;"; // SQL语句
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            String allKey = "";
            while (rs.next()) {
                allKey += rs.getString(1);
            }
            return allKey;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            // 释放数据集对象
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            // 释放语句对象
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } 
    }
    
    /**
     * 
     * 获取top10关键词Map
     * @param allkw
     * @return
     */
    public List<Map.Entry<String, Integer>> getHotkw (String str){
        int num = 0;
        TreeMap<String, Integer> map = new TreeMap<>();
        String lowerStr = str.toLowerCase();
        String[] word = lowerStr.split(",");
        int len = word.length;
        String tw = null;
        for (int i = 0; i < len; i++) {
            tw = word[i];
               if (!map.containsKey(tw)) {
                   map.put(tw, 1);
               } 
               else {
                   num = map.get(tw);
                   map.put(tw, num + 1);
               }
        }
        List<Map.Entry<String, Integer>> hotWords = WordCountMethod.highFreqWord(map);
        return hotWords;
        
    }
    
    /**
     * 返回热词
     * @param highFreqList
     * @return
     */
    public String[] words(List<Map.Entry<String, Integer>> highFreqList) {
        String[] kw = new String[10];
        for (int i = 0; i < highFreqList.size(); i++) {
            Map.Entry<String, Integer> temp = highFreqList.get(i);
            kw[i] = temp.getKey();

            System.out.println(kw[i]);
        }
        return kw;
    }
    
    /**
     * 返回热词数量
     * @param highFreqList
     * @return
     */
    public int[] num(List<Map.Entry<String, Integer>> highFreqList) {
        int[] num = new int [10];
        for (int i = 0; i < highFreqList.size(); i++) {
            Map.Entry<String, Integer> temp = highFreqList.get(i);
            num[i] = temp.getValue();

            System.out.println(num[i]);
        }
        return num;
    }
    
    /**
     * 获取排名第一热词3年的出现数据
     * @return
     */
    public int key1YearNum() {
        String allKw = getKeywordsFromDB();
        String[] topkw = words(getHotkw(allKw));
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBHelper.getConnection();
            String sql = "select * from paperslist where conference like ? and keyword like ?;"; // SQL语句
            stmt = conn.prepareStatement(sql);
            String y2016 = "%2016%";
            String kw1 = "%"+ topkw[0] + "%";
            stmt.setString(1, y2016);
            stmt.setString(2, kw1);
            rs = stmt.executeQuery();
            int num2016 = 0;
            while (rs.next()) {
                num2016++;
            }
            System.out.println(num2016);
            return num2016;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        } finally {
            // 释放数据集对象
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            // 释放语句对象
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
}
