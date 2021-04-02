<?php

require_once(dirname(__FILE__)."\\..\\models\\Paper.php");
require_once(dirname(__FILE__)."\\..\\utils\\DbUtil.php");
require_once(dirname(__FILE__)."\\..\\dao\\KeywordDao.php");

class PaperDao
{
    public static function getPapersByTitle($title) : array
    {
        $con = DbUtil::getConnection();
        $sql = "select * from paper where title like '%\"$title\"%'";
        $result = $con->query($sql);
        $papers = array();
        while ($row = $result->fetch_assoc())
        {
            $paper = Paper::InstancePaperFromRow($row);
            $paper->keywords = KeywordDao::getKeywordsByPaperID($paper->id);
            array_push($papers, $paper);
        }
        return $papers;
    }

    public static function getPapers(string $title = null, 
                                    string $keyword, 
                                    string $year = null, string $forum = null, 
                                    string $id = null) : array
    {

        $sqlSelect = "select paper.id, paper.title, paper.abstract, paper.link, paper.year, paper.forum ";
        $sqlTables = "from paper ";
        if ($keyword != null)
            $sqlTables .= ", keyword_paper, keyword ";
        $sqlWhere = "where 1 = 1 ";
        if ($id != null)
            $sqlWhere .= "and paper.id = $id ";
        if ($title != null)
            $sqlWhere .= "and title like '%$title%' ";
        if ($year != null)
            $sqlWhere .= "and year = $year ";
        if ($forum != null)
            $sqlWhere .= "and forum = '$forum' ";
        if ($keyword != null)
        {
            $sqlWhere .= "and paper.id = keyword_paper.paperid and keyword_paper.keyid = keyword.id and keyword.keyword = '$keyword' ";
        }

        $sqlCount = "select count(*) as count ".$sqlTables.$sqlWhere.";";
        $sqlSearch = $sqlSelect.$sqlTables.$sqlWhere."limit 1000".";";
        $result = array();
        $con = DbUtil::getConnection();
        // var_dump($sqlCount);
        // var_dump($sqlSearch);
        if ($con->multi_query($sqlCount.$sqlSearch))
        {
            $rs = $con->store_result();
            $result['total'] = $rs->fetch_row()[0];
            $rs->free();
            $con->next_result();
            $rs = $con->store_result();
            $papers = array();
            while ($row = $rs->fetch_assoc())
            {
                $paper = Paper::InstancePaperFromRow($row);
                $paper->keywords = KeywordDao::getKeywordsByPaperID($paper->id);
                array_push($papers, $paper);
            }
            $result['papers'] = $papers;
        }
        return $result;
    }

    public static function getPapersByKeyword($keyword) : array
    {
        $keyid = KeywordDao::getKeywordIDByKeyword($keyword);
        $sql = "select * from paper, keyword_paper ".
        "where paper.id = keyword_paper.paperid and keyword_paper.keyid = $keyid";
        $con = DbUtil::getConnection();
        $result = $con->query($sql);
        $papers = array();
        while ($row = $result->fetch_assoc())
        {
            $paper = Paper::InstancePaperFromRow($row);
            $paper->keywords = KeywordDao::getKeywordsByPaperID($paper->id);
            array_push($papers, $paper);
        }
        return $papers;
    }

    public static function getPaperByID($paperID) : Paper
    {
        $con = DbUtil::getConnection();
        $sql = "select * from paper where id = $paperID";
        $result = $con->query($sql);
        $row = $result->fetch_assoc();
        $paper = Paper::InstancePaperFromRow($row);
        $paper->keywords = KeywordDao::getKeywordsByPaperID($paperID);
        return $paper;
    }

    public static function getAddPaperSQL(Paper $paper)
    {
        $keywords = $paper->keywords;
        $sql = "";
        if ($keywords !== null)
        {
            foreach ($keywords as $keyword)
            {
                $sql .= KeywordDao::getAddKeywordSQL($keyword);
            }
        }
        $sql .= "insert into paper(id, title, abstract, link, year, forum) ".
        "values($paper->id, '$paper->title', '$paper->abstract', '$paper->link', $paper->year, '$paper->forum');";
        $con = DbUtil::getConnection();
        $con->query($sql);
        if ($keywords != null)
        {
            foreach ($keywords as $keyword)
            {
                $sql .= KeywordDao::getAddKeywordPaperSQL($keyword, $paper->id);
            }
        }
        return $sql;
    }
}