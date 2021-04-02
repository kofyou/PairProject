<?php

require_once(dirname(__FILE__ ) . "\\..\\dao\\StatisticsDao.php");

$result = StatisticsDao::getTrendOfPopularWords();

//var_dump($result);
require_once(dirname(__FILE__)."\\..\\view\\statistics.php");