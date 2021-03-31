<?php

require_once(dirname(__FILE__)."\\..\\utils\\DbUtil.php");

class StatisticsDao
{
    static public function getTop10Keywords()
    {
        $sql = "select * from keyword order by count desc limit 10";
        $con = DbUtil::getConnection();
        $rs = $con->query($sql);
        $popularWords = array();
        while ($row = $rs->fetch_assoc())
        {
            $keyword = $row['keyword'];
            $count = intval($row['count']);
            $popularWords[$keyword] = $count;
        }
        return $popularWords;
    }

    static public function getTrendOfPopularWords()
    {
        
    }
}