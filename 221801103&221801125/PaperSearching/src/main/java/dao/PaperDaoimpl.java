package dao;

import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.mysql.cj.jdbc.ConnectionImpl;
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
                    "insert into papers values(?,?,?,?,?,?,?,?,?)");
            for(Paper paper:paperinfos)
            {
                preparedStatement.setString(1, paper.getIsbn());
                preparedStatement.setString(2, paper.getTitle());
                preparedStatement.setString(3, paper.getAuthors());
                preparedStatement.setString(4, paper.getKeywords());
                preparedStatement.setString(5, paper.getTheabstract());
                preparedStatement.setString(6, paper.getPublishDate());
                preparedStatement.setString(7, paper.getConferrence());
                preparedStatement.setString(8, paper.getPaperlink());
                preparedStatement.setDate(9,paper.getDate());
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
            preparedStatement.setString(1,title);
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

    public List<Paper> GetPaticularPapers(String searchWords,int type)
    {
        List<Paper> papers=new ArrayList<>();
        try
        {
            Connection connection=Jdbcutils.GetConnection();

            if (type==0)
            {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT  * FROM papers WHERE title LIKE ? OR keywords LIKE ? limit 160");


                preparedStatement.setString(1,"%"+searchWords+"%");
                preparedStatement.setString(2,"%"+searchWords+"%");
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
                hashMap.put(resultSet.getString("keyword"), resultSet.getInt("total"));
            }
            Jdbcutils.CloseConnection(preparedStatement,connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return hashMap;
    }

    public boolean IsCollected(String account,String title)
    {
        int n=0;
        try
        {
            Connection connection=Jdbcutils.GetConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "SELECT * from usercollect where account=?" +
                            " AND title=?");
            preparedStatement.setString(1,account);
            preparedStatement.setString(2,title);

            ResultSet resultSet= preparedStatement.executeQuery();
            while(resultSet.next())
            {
                n++;
            }

            Jdbcutils.CloseConnection(resultSet,preparedStatement,connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(n!=0)
        {
            return true;
        }
        return false;
    }

    public List<Paper> GetMyCollect(String username)
    {
        List<Paper> papers=new ArrayList<>();
        try
        {
            Connection connection=Jdbcutils.GetConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM papers WHERE title IN " +
                            "(SELECT title FROM usercollect WHERE account=?)");
            preparedStatement.setString(1,username);
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

            Jdbcutils.CloseConnection(resultSet,preparedStatement,connection);

            return papers;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void UpdateMyCollect(String account,String title)
    {
        try
        {
            Connection connection=Jdbcutils.GetConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "INSERT into usercollect VALUES(?,?)");
            preparedStatement.setString(1,account);
            preparedStatement.setString(2,title);
            preparedStatement.execute();

            Jdbcutils.CloseConnection(preparedStatement,connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void DeleteMyCollect(String account,String title)
    {
        try
        {
            Connection connection=Jdbcutils.GetConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "DELETE from usercollect where account=? and title=?");
            preparedStatement.setString(1,account);
            preparedStatement.setString(2,title);
            preparedStatement.execute();

            Jdbcutils.CloseConnection(preparedStatement,connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public HashMap<String,Integer> GetConferencesCount()
    {
        HashMap<String,Integer> hashMap=new HashMap<>();
        try
        {
            Connection connection=Jdbcutils.GetConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "select conference as conference,count(*) as count from papers GROUP BY conference");
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next())
            {
                hashMap.put(resultSet.getString("conference"),resultSet.getInt("count"));
            }

            Jdbcutils.CloseConnection(resultSet,preparedStatement,connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return hashMap;
    }
}
