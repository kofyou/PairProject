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

    public static function getAddKeywordSQL($keyword)
    {
        return "insert into keyword(keyword) values('$keyword') on duplicate key update count = count + 1;";
    }

    public static function getAddKeywordPaperSQL($keyword, $paperid)
    {
        $sql = "insert into keyword_paper(keyid, paperid) ".
        "select keyword.id, $paperid from keyword where keyword.keyword = '$keyword';";
        return $sql;
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