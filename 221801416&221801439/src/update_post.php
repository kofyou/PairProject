<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
  <head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="new_post.css" type="text/css" />
    <title>修改文章</title>
  </head>
  <body>
  <h1 id="title">当前文章</h1>
  <p id="limit">(仅限英文)</p>
  <?php
  require('config.php');

  $db = new mysqli($db_host, 	$db_username, $db_password, $db_database);

  if (mysqli_connect_errno()) {
     echo '错误: 无法连接到数据库. 请稍后再次重试.';
     exit;
  }
    // 设置字符集
  $db->query("SET NAMES utf8");

  $query = "select post_content from paper where post_title='$_GET[title]'";
  $result = $db->query($query);

  $row = $result->fetch_assoc(); //返回记录集第一行
  ?>
 <div class="new_post">
  <form action="update_submit.php" method="post">
    <p>标题:</p>
    <p><input type="text" name="title" maxlength="60" size="30" id="tips" value="<?php $_title = $_GET["title"];echo $_GET["title"];?>"/></p>
    <p>正文:</p>
    <p><textarea  name = "content" rows="8" cols="60" id="passage" ><?php  echo $row['post_content']; ?></textarea></p>
    <p><input type="hidden" name="oldtitle" value="<?php echo $_GET["title"];?>"/></p> 
    <p><input type="submit" value="修改" id="submit"/></p>
  </form>
  </div>
