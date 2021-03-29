package com.example.partnerwork;


import java.sql.*;
import java.util.*;

public class PaperDao {
    private static com.example.partnerwork.PaperDao paperDao;
    private List<Paper> paperList;
    private Map<String, Integer> tagMap;
    private List<HashMap.Entry<String, Integer>> sortTagList;

    private PaperDao(){}

    public static com.example.partnerwork.PaperDao getInstance(){
        if (paperDao == null){
            paperDao = new com.example.partnerwork.PaperDao();
        }
        return paperDao;
    }

    public Paper findPaperByIterator(int i){
        return paperList.get(i);
    }

    public int getTotal(){
        return paperList.size();
    }

    public Paper find(int id){
        Paper paper = new Paper();
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from paper where id="+id;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                paper.setId(rs.getInt("id"));
                paper.setTitle(rs.getString("title"));
                paper.setAbstractText(rs.getString("abstract"));
                paper.setKeywords(rs.getString("keywords"));
                paper.setTagList();
                paper.setDoiLink(rs.getString("doiLink"));
                paper.setPublicationDate(rs.getInt("publicationDate"));
                paper.setConference(rs.getString("conference"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paper;
    }

    public List<Paper> find(String findString){
        if (findString.trim().equals("")){
            list();
            return list(1, 8);
        }
        findString = findString.trim().toLowerCase();
        paperList = new ArrayList<Paper>();
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from paper where lower(title) like '%"+findString+"%'";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Paper paper = new Paper();
                paper.setId(rs.getInt("id"));
                paper.setTitle(rs.getString("title"));
                paper.setAbstractText(rs.getString("abstract"));
                paper.setKeywords(rs.getString("keywords"));
                paper.setTagList();
                paper.setDoiLink(rs.getString("doiLink"));
                paper.setPublicationDate(rs.getInt("publicationDate"));
                paper.setConference(rs.getString("conference"));
                paperList.add(paper);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list(1, 8);
    }

    public List<Paper> findByTag(String tag){
        tag = tag.trim().toLowerCase();
        paperList = new ArrayList<Paper>();
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from paper where lower(keywords) like '%" + tag + "%'";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Paper paper = new Paper();
                paper.setId(rs.getInt("id"));
                paper.setTitle(rs.getString("title"));
                paper.setAbstractText(rs.getString("abstract"));
                paper.setKeywords(rs.getString("keywords"));
                paper.setTagList();
                paper.setDoiLink(rs.getString("doiLink"));
                paper.setPublicationDate(rs.getInt("publicationDate"));
                paper.setConference(rs.getString("conference"));
                paperList.add(paper);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list(1, 8);
    }

    public void add(Paper bean){
        String sql = "insert into paper values(null ,? ,? ,? ,? ,? ,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, bean.getTitle());
            ps.setString(2, bean.getAbstractText());
            ps.setString(3, bean.getKeywords());
            ps.setString(4, bean.getDoiLink());
            ps.setInt(5, bean.getPublicationDate());
            ps.setString(6, bean.getConference());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void add(List<Paper> list){
        String sql = "insert into paper values(null ,? ,? ,? ,? ,? ,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (Paper bean : list){
                ps.setString(1, bean.getTitle());
                ps.setString(2, bean.getAbstractText());
                ps.setString(3, bean.getKeywords());
                ps.setString(4, bean.getDoiLink());
                ps.setInt(5, bean.getPublicationDate());
                ps.setString(6, bean.getConference());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    bean.setId(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int iterator){
        int id = paperList.get(iterator).getId();
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "delete from paper where id="+id;
            s.execute(sql);
            paperList.remove(iterator);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Paper> list(){
        paperList = new ArrayList<Paper>();
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from paper";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Paper paper = new Paper();
                paper.setId(rs.getInt("id"));
                paper.setTitle(rs.getString("title"));
                paper.setAbstractText(rs.getString("abstract"));
                paper.setKeywords(rs.getString("keywords"));
                paper.setTagList();
                paper.setDoiLink(rs.getString("doiLink"));
                paper.setPublicationDate(rs.getInt("publicationDate"));
                paper.setConference(rs.getString("conference"));
                paperList.add(paper);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paperList;
    }

    public List<Paper> list(int start, int count){
        if (paperList == null){
            list();
            setTagMap();
            setSortTagList();
            getSortTagList();
        }
        List<Paper> list = new ArrayList<Paper>();
        if (start * count > paperList.size()){
            for (int i = (start - 1) * count;i < paperList.size();i ++){
                list.add(paperList.get(i));
            }
        }
        else{
            list = paperList.subList((start - 1) * count , (start - 1) * count + count);
        }
        return list;
    }

    public void setTagMap(){
        tagMap = new HashMap<String, Integer>();
        for (Paper paper : paperList){
            for (String tag : paper.getTagList()){
                if (tagMap.containsKey(tag)) {
                    int n = tagMap.get(tag);
                    tagMap.put(tag, n + 1);
                } else {
                    tagMap.put(tag, 1);
                }
            }
        }
    }

    public void setSortTagList(){
        sortTagList = new ArrayList<>(tagMap.entrySet());

        Collections.sort(sortTagList, new Comparator<HashMap.Entry<String, Integer>>() {
            @Override
            public int compare(HashMap.Entry<String, Integer> tag1, HashMap.Entry<String, Integer> tag2) {
                if (tag1.getValue().equals(tag2.getValue())) {
                    return tag1.getKey().compareTo(tag2.getKey());
                } else {
                    return tag2.getValue() - tag1.getValue();
                }
            }
        });
        sortTagList = sortTagList.subList(0, 10);
    }

    public List<HashMap.Entry<String, Integer>> getSortTagList() {
        for (HashMap.Entry entry : sortTagList) {
            System.out.println(entry.getKey()+ ": "+ entry.getValue());
        }
        return sortTagList;
    }
}
