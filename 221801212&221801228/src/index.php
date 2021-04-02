<?php
if (array_key_exists("statistics", $_REQUEST))
    require_once(dirname(__FILE__)."\\controlers\\statisticsControler.php");
else  
    require_once(dirname(__FILE__)."\\controlers\\searchControler.php");

