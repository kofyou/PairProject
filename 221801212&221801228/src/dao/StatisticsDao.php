<?php

require_once(dirname(__FILE__) . "\\..\\utils\\DbUtil.php");

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
        $forums = ['ECCV', 'CVPR', 'ICCV'];
        $keywords = array();
        foreach ($forums as $forum) 
        {
            $sql = 'select keyword from paper, keyword_paper, keyword
            where paper.id = keyword_paper.paperid and keyword.id = keyword_paper.keyid
            and forum = "'.$forum.'"
            group by keyword
            order by count(keyword) desc limit 1;';
            $con = DbUtil::getConnection();
            $result = $con->query($sql);
            array_push($keywords, $result->fetch_array()[0]);
        }
        $statistics = array();
        for ($i = 0; $i < sizeof($keywords); $i++)
        {
            $sql = "select count(year) as count, year as num from keyword, keyword_paper, paper
            where keyword.id = keyword_paper.keyid and keyword = '".$keywords[$i]."' and paper.id = keyword_paper.paperid and forum = '".$forums[$i]."'
            group by year;";
            $result = $con->query($sql);
            $yearCount = array();
            while ($row = $result->fetch_array())
            {
                $year = $row[1];
                $count = intval($row[0]);
                $yearCount[$year] = $count;
            }
            $stat = ['forum' => $forums[$i], 'keyword' => $keywords[$i], 'statistics' => $yearCount];
            array_push($statistics, $stat);
        }
        return $statistics;
    }
}
