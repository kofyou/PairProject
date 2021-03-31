<?php

require_once(dirname(__FILE__)."\\..\\dao\\KeywordDao.php");
require_once(dirname(__FILE__)."\\..\\dao\\PaperDao.php");

function open_all_file($dir, $forumName, $read_json)
{
    $handler = opendir($dir);
    if ($handler === null)
        return;

    while ($subdir = readdir($handler))
    {
        if ($subdir == "." || $subdir == "..")
        {
            continue;
        }
        $fullPath = realpath($dir.'/'.$subdir);
        $sql = "";
        if (is_file($fullPath))
        {
            $paper = $read_json($fullPath, $forumName);
            $sql .= PaperDao::getAddPaperSQL($paper);
            //echo $sql."\n";
            //break;
            //var_dump($paper->keywords);
            echo $paper->title."\n";
            DbUtil::queryNoResult($sql);
            $sql = "";
        }
    }
}

function read_paper_from_json($fileName, $forumName) : Paper
{
    $handler = fopen($fileName, "r");
    $json = json_decode(trim(fread($handler, filesize($fileName)), ";"), true);
    fclose($handler);
    $json['forum'] = $forumName;
    $paper = Paper::InstancePaperFromJson($json);
    if (array_key_exists("keywords", $json))
    {
        $keywords = array();
        $arr = $json['keywords'];
        foreach ($arr as $element)
        {
            $ks = $element['kwd'];
            foreach ($ks as $keyword)
            {
                if (!in_array($keyword, $keywords))
                {
                    array_push($keywords, $keyword);
                }
            }
        }
        $paper->keywords = $keywords;
    }
    return $paper;
}

function read_paper_from_json_ECCV($fileName, $forumName)
{
    static $id = 9157828;
    $handler = fopen($fileName, "r");
    $json = json_decode(trim(fread($handler, filesize($fileName)), ";"), true);
    fclose($handler);
    $json['forum'] = $forumName;
    $json['title'] = $json['论文名称'];
    $json['abstract'] = $json['摘要'];
    $json['doiLink'] = $json['原文链接'];
    $json['articleId'] = $id;
    $id++;
    $json['publicationYear'] = intval(explode(" ", $json['会议和年份'])[1]);
    $paper = Paper::InstancePaperFromJson($json);
    if (array_key_exists('关键词', $json))
    {
        $keywords = array();
        $arr = $json['关键词'];
        foreach ($arr as $keyword)
        {
            array_push($keywords, $keyword);
        }
        $paper->keywords = $keywords;
    }
    //var_dump($json);
    //var_dump($paper);
    return $paper;
}

// open_all_file("datas/CVPR", "CVPR", "read_paper_from_json");

// open_all_file("datas/ICCV", "ICCV", "read_paper_from_json");

open_all_file("datas/ECCV", "ECCV", "read_paper_from_json_ECCV");
