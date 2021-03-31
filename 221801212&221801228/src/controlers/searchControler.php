<?php

require_once(dirname(__FILE__) . "\\..\\dao\\PaperDao.php");
require_once(dirname(__FILE__) . "\\..\\dao\\StatisticsDao.php");

$title = "";
$keyword = "";
$year = "";
$forum = "";
if (array_key_exists('title', $_REQUEST))
    $title = trim($_REQUEST['title']);
if (array_key_exists('keyword', $_REQUEST))
    $keyword = trim($_REQUEST['keyword']);
if (array_key_exists('year', $_REQUEST) && is_numeric($_REQUEST['year']))
    $year = trim($_REQUEST['year']);
if (array_key_exists('forum', $_REQUEST))
    $forum = trim($_REQUEST['forum']);
$result = array();
if ($title != "" || $keyword != "" || $year != "" | $forum != "")
{
    $result = PaperDao::getPapers($title, $keyword, $year, $forum);
    $result['isFirst'] = false;
}
else 
{
    $result['isFirst'] = true;
}

$result['title'] = $title;
$result['keyword'] = $keyword;
$result['year'] = $year;
$result['forum'] = $forum;
$result['popularWords'] = StatisticsDao::getTop10Keywords();
require_once(dirname(__FILE__) . "\\..\\view\\search.php");
