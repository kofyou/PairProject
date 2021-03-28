<?php

require_once(dirname(__FILE__)."\\..\\utils\\DbUtil.php");

class KeywordDao
{
    public static function getKeywordsByPaperID($paperID) : array
    {
        $con = DbUtil::getConnection();
        $sql = "select keyword from keyword, keyword_paper where paperid = $paperID and id = keyid";
        $result = $con->query($sql);
        $keywords = array();
        while ($row = $result->fetch_assoc())
        {
            array_push($keywords, $row['keyword']);
        }
        return $keywords;
    }

    public static function addKeyword($keyword)
    {
        $con = DbUtil::getConnection();
        $sql = "insert into keyword(keyword) values($keyword) on duplicate key update count = count + 1";
        $con->query($sql);
    }

    public static function addKeywordPaper($keyword, $paperid)
    {
        $con = DbUtil::getConnection();
        $sql = "insert into keyword_paper(keyid, paperid) ".
        "select keyword.id, $paperid from keyword where keyword.keyword = '$keyword'";
    }
    public static function getKeywodByKeywordID($keywordID)
    {
        $con = DbUtil::getConnection();
        $sql = "select keyword from keyword where id = $keywordID";
        $result = $con->query($sql);
        return $result->fetch_assoc()['keyword'];
    }

    public static function getKeywordIDByKeyword($keyword)
    {
        $con = DbUtil::getConnection();
        $sql = "select id from keyword where keyword = '$keyword'";
        $result = $con->query($sql);
        return $result->fetch_assoc()['keyword'];
    }
}