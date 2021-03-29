<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
  <head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="list_post.css" type="text/css" />
    <title>显示文章列表</title>
  </head>
  <body>
<?php
 //echo $_GET['keyword'];
 require('config.php');

 $db = new mysqli($db_host, 	$db_username, $db_password, $db_database);

 if (mysqli_connect_errno()) {
    echo '错误: 无法连接到数据库. 请稍后再次重试.';
    exit;
 }
   // 设置字符集
 $db->query("SET NAMES utf8");
 
 $query = "select * from paper where keywords like '%".$_GET['keyword']."%'";
 $result = $db->query($query);

 $num_results = $result->num_rows;

 echo '<p id="num">共有文章: '.$num_results.' 篇</p>';

 for ($i=0; $i <$num_results; $i++)
 {
    $row = $result->fetch_assoc();
    echo '<div class="list"><h2>'.($i+1).'.  ' . htmlspecialchars(stripslashes($row['post_title'])) . "</h2>\n".
     '<p>发表时间：' . htmlspecialchars(stripslashes($row['release_date'])) . "</p>\n".
     '<p>会议时间：' . htmlspecialchars(stripslashes($row['meeting_date'])) . "</p>\n".
     '<p>操作：'.'<a href="update_post.php?title='.htmlspecialchars(stripslashes($row['post_title'])).'"><button  id="link">修改</button></a>'.
     '  '.'<a href="delete_post.php?title='.htmlspecialchars(stripslashes($row['post_title'])).'"><button  id="link">删除</button></a>'.
     '<p>摘要：'.nl2br(htmlspecialchars(stripslashes($row['post_content']))).'</p>'.
     '<a href="'.htmlspecialchars(stripslashes($row['link'])).'"><button  id="link">原文链接</button></a></div>';
 }

 $result->free();
 $db->close();
?>
</body>
</html>