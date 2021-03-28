<?php
//require_once("db.config.php");
require_once(dirname(__FILE__)."\\..\\config\\db.config.php");
class DbUtil
{
    private static $db = null;
    private function __construct()
    {
    }

    public static function getConnection() : mysqli
    {
        if (self::$db === null)
        {
            $config = db_config();
            $db = new mysqli($config['servername'],
                                $config['username'],
                                $config['password'],
                                $config['dbname']);
        }
        return $db;
    }
}