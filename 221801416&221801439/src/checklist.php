<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
  <head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <title>Simple Blog System—发表文章</title>
  </head>
  <body>
  <h1>Simple Blog System—文章列表</h1>
  <a href="new_post.html">新增文章</a>
  <br/>
  <br/>
  <form action = "list_post.php" method="post">
    <input type="text" name="title" value="请输入标题关键字进行搜索"  onfocus='if(this.value=="请输入标题关键字进行搜索"){this.value="";}; '   
            onblur='if(this.value==""){this.value="请输入标题关键字进行搜索";};'/>
    <select name="time">
      <option value="所有时间">选择会议时间</option>
      <option value="2007">2007</option>
      <option value="2008">2008</option>
      <option value="2009">2009</option>
      <option value="2010">2010</option>
      <option value="2011">2011</option>
      <option value="2012">2012</option>
      <option value="2013">2013</option>
      <option value="2014">2014</option>
      <option value="2015">2015</option>
      <option value="2016">2016</option>
      <option value="2017">2017</option>
      <option value="2018">2018</option>
      <option value="2019">2019</option>
    </select>
    <input type="submit" value="搜索"/>
    <select name="sort">选择排序方式
      <option value="post_title">标题</option>
      <option value="meeting_date">年份</option>
    </select>
    <input type="submit" value="排序"/>
  </form>
  <form action="loginout.php" method="post">
    <input type="submit" value="退出登录"/>
  </form>
<?php
  require('config.php');

  $db = new mysqli($db_host, 	$db_username, $db_password, $db_database);

  if (mysqli_connect_errno()) {
     echo '错误: 无法连接到数据库. 请稍后再次重试.';
     exit;
  }
    // 设置字符集
  $db->query("SET NAMES utf8");
  $title = $_POST["title"];
  $time = $_POST["time"];
  $sort = $_POST["sort"];
  if($title != "请输入标题关键字进行搜索" && $title != null && $time != "所有时间") {
    $query = "select * from paper where post_title like '%".$title."%' AND meeting_date like '%".$time."' order by ".$sort;
  }
  else if(($title == "请输入标题关键字进行搜索" || $title == null) && $time != "所有时间") {
    $query = "select * from paper where meeting_date like '%".$time."' order by ".$sort;
  }
  else if(($title != "请输入标题关键字进行搜索" && $title != null) && $time == "所有时间") {
    $query = "select * from paper where post_title like '%".$title."%' order by ".$sort;
  }
  else {
    $query = "select * from paper order by ".$sort;
  }
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
</body>
</html>
