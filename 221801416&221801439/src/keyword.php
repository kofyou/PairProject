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

 echo '<p>共有文章: '.$num_results.' 篇</p>';

 for ($i=0; $i <$num_results; $i++)
 {
    $row = $result->fetch_assoc();
    echo '<h2>'.($i+1).'.  ' . htmlspecialchars(stripslashes($row['post_title'])) . "</h2>\n";
    echo '<p>发表时间：' . htmlspecialchars(stripslashes($row['release_date'])) . "</p>\n";
    echo '<p>会议时间：' . htmlspecialchars(stripslashes($row['meeting_date'])) . "</p>\n";
    echo '<p>操作：'.'<a href="update_post.php?title='.htmlspecialchars(stripslashes($row['post_title'])).'">修改</a>';
    echo '  '.'<a href="delete_post.php?title='.htmlspecialchars(stripslashes($row['post_title'])).'">删除</a>';
    echo '<p>摘要：'.nl2br(htmlspecialchars(stripslashes($row['post_content']))).'</p>';
    echo '<a href="'.htmlspecialchars(stripslashes($row['link'])).'">原文链接</a>';
 }

 $result->free();
 $db->close();
?>