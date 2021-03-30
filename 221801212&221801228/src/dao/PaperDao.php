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

    public static function getPapers(int $currentPage, 
                                    int $pageSize, string $title = null, 
                                    string $keyword, 
                                    string $year = null, string $forum = null, 
                                    string $id = null) : array
    {
        $sqlTables = "select id, title, abstract, link, year, forum from paper ";
        if ($keyword != null)
            $sqlTables .= "keyword_paper, keyword ";
        $sqlWhere = "where 1 = 1 ";
        if ($id != null)
            $sqlWhere .= "paper.id = $id ";
        if ($title != null)
            $sqlWhere .= "title like '%$title%' ";
        if ($year != null)
            $sqlWhere .= "year = $year";
        if ($forum != null)
            $sqlWhere .= "forum = '$forum' ";
        if ($keyword != null)
        {
            $sqlWhere .= "paper.id = keyword_paper.paperid 
            and keyword_paper.keyid = keyword.id 
            and keyword.keyword = '$keyword' ";
        }

        $sqlCount = "select count(*) as count ".$sqlWhere.";";

        if ($currentPage < 1)
            $currentPage = 1;
        if ($pageSize < 1)
            $pageSize = 5;
        
        $offset = ($currentPage - 1) * $pageSize;
        $sqlSearch = $sqlTables.$sqlWhere."limit $offset, $pageSize;";

        $result = array();
        $con = DbUtil::getConnection();
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
        $result['pageSize'] = $pageSize;
        $result['currentPage'] = $currentPage;
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