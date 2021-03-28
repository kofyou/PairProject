package dao;

import pojo.Paper;
import sun.util.resources.cldr.so.CalendarData_so_ET;
import utils.Jdbcutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                paper.setIsbn("isbn");
                paper.setTitle("title");
                paper.setAuthors("authors");
                paper.setKeywords("keywords");
                paper.setTheabstract("abstract");
                paper.setPublishDate("publishdate");
                paper.setConferrence("conference");
                paper.setPaperlink("paperlink");
            }

            Jdbcutils.CloseConnection(resultSet,preparedStatement,connection);

            return paper;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
