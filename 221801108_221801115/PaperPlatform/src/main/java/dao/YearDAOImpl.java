package dao;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class YearDAOImpl implements YearDAO{

    /* 返回数据库中论文所有的发表年份 */
    public ArrayList<Integer> getYears() {
        ArrayList<Integer> years = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            conn = DBUtil.getConnection();
            pstm = conn.prepareStatement("select year from paper group by year order by year");
            rs = pstm.executeQuery();

            while (rs.next()) {
                years.add(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstm, conn);
        }
        return years;
    }

}
