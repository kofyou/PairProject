package dao;

import com.mysql.cj.jdbc.ClientPreparedStatement;
import pojo.Paper;
import sun.util.resources.cldr.so.CalendarData_so_ET;
import utils.Jdbcutils;

import javax.swing.text.html.HTMLDocument;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaperDaoimpl
{

    public void PaperStorage(List<Paper> paperinfos)
    {
        try
        {
            Connection connection= Jdbcutils.GetConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "insert into papers values(?,?,?,?,?,?,?,?)");
            for(int i=0;i<paperinfos.size();i++)
            {
                preparedStatement.setString(1, paperinfos.get(i).getIsbn());
                preparedStatement.setString(2, paperinfos.get(i).getTitle());
                preparedStatement.setString(3, paperinfos.get(i).getAuthors());
                preparedStatement.setString(4, paperinfos.get(i).getKeywords());
                preparedStatement.setString(5, paperinfos.get(i).getTheabstract());
                preparedStatement.setString(6, paperinfos.get(i).getPublishDate());
                preparedStatement.setString(7, paperinfos.get(i).getConferrence());
                preparedStatement.setString(8, paperinfos.get(i).getPaperlink());
                preparedStatement.addBatch();

            }
            preparedStatement.executeBatch();

            Jdbcutils.CloseConnection(preparedStatement,connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Paper GetAPaper(String title)
    {
        try
        {
            Paper paper=new Paper();
            Connection connection=Jdbcutils.GetConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "select  * from papers where title=?");
            ResultSet resultSet= preparedStatement.executeQuery();
            while(resultSet.next())
            {
                paper.setIsbn(resultSet.getString("isbn"));
                paper.setTitle(resultSet.getString("title"));
                paper.setAuthors(resultSet.getString("authors"));
                paper.setKeywords(resultSet.getString("keywords"));
                paper.setTheabstract(resultSet.getString("abstract"));
                paper.setPublishDate(resultSet.getString("publishdate"));
                paper.setConferrence(resultSet.getString("conference"));
                paper.setPaperlink(resultSet.getString("paperlink"));
            }

            Jdbcutils.CloseConnection(resultSet,preparedStatement,connection);

            return paper;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public List<Paper> GetPaticularPapers(String[] searchWords,int type)
    {
        List<Paper> papers=new ArrayList<>();
        try
        {
            Connection connection=Jdbcutils.GetConnection();

            if (type==0)
            {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM papers WHERE title LIKE ''%'?'%'' OR keywords LIKE ''%'?'%''");

                for(int i=0;i<searchWords.length;i++)
                {
                    preparedStatement.setString(1,searchWords[i]);
                    preparedStatement.setString(2,searchWords[i]);
                    ResultSet resultSet=preparedStatement.executeQuery();
                    while(resultSet.next())
                    {
                        Paper paper=new Paper();
                        paper.setIsbn(resultSet.getString("isbn"));
                        paper.setTitle(resultSet.getString("title"));
                        paper.setAuthors(resultSet.getString("authors"));
                        paper.setKeywords(resultSet.getString("keywords"));
                        paper.setTheabstract(resultSet.getString("abstract"));
                        paper.setPublishDate(resultSet.getString("publishdate"));
                        paper.setConferrence(resultSet.getString("conference"));
                        paper.setPaperlink(resultSet.getString("paperlink"));
                        papers.add(paper);
                    }
                }

            }
            else
            {
                PreparedStatement preparedStatement=connection.prepareStatement(
                        "SELECT * FROM papers");
                ResultSet resultSet =preparedStatement.executeQuery();
                while(resultSet.next())
                {
                    Paper paper=new Paper();
                    paper.setIsbn(resultSet.getString("isbn"));
                    paper.setTitle(resultSet.getString("title"));
                    paper.setAuthors(resultSet.getString("authors"));
                    paper.setKeywords(resultSet.getString("keywords"));
                    paper.setTheabstract(resultSet.getString("abstract"));
                    paper.setPublishDate(resultSet.getString("publishdate"));
                    paper.setConferrence(resultSet.getString("conference"));
                    paper.setPaperlink(resultSet.getString("paperlink"));
                    papers.add(paper);
                }
            }
            return papers;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void InsertKeyword(HashMap<String,Integer> hashMap)
    {
        try
        {
            Connection connection = Jdbcutils.GetConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO keyword values(?,?)");
            for (HashMap.Entry<String, Integer> entry : hashMap.entrySet())
            {
                preparedStatement.setString(1,entry.getKey());
                preparedStatement.setInt(2,entry.getValue());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();

            Jdbcutils.CloseConnection(preparedStatement,connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public HashMap<String,Integer> GetHottestKeywords()
    {
        HashMap<String,Integer> hashMap=new HashMap<>();
        try
        {
            Connection connection = Jdbcutils.GetConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from keyword order by total desc limit 0,10");

            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next())
            {
                hashMap.put(resultSet.getString("keyword"), resultSet.getInt("tatol"));
            }
            Jdbcutils.CloseConnection(preparedStatement,connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return hashMap;
    }
}
