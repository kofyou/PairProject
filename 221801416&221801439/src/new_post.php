<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
  <head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <title>发表文章</title>
  </head>
  <body>
  <h1>发表文章</h1>
<?php
  require('config.php');
  date_default_timezone_set('Asia/Shanghai');
  
  $title=$_POST['title'];
  $content=$_POST['content'];

  if (!$title || !$content) {
     echo '你未输入文章的标题或正文.<br />'
          .'请退回再次重试.';
     exit;
  }
  /*if (!get_magic_quotes_gpc()) {
    $title = addslashes($title);
    $body = addslashes($content);
  }*/

  @ $db = new mysqli($db_host, 	$db_username, $db_password, $db_database);

  if (mysqli_connect_errno()) {
     echo '错误: 无法连接到数据库. 请稍后再次重试.';
     exit;
  }
  // 设置字符集
  $db->query("SET NAMES utf8");

  $query = "select * from paper";
  $result = $db->query($query);

  $num_results = $result->num_rows;
  
  $num = $num_results + 1;
  $query = "insert into paper values('" . $title . "', '"  . $content . "', NULL, NULL,'" . date('Y-m-d H:i:s') . "', NULL)";
  echo '<pre>' . $query . '</pre>' ;

  $result = $db->query($query);
  if ($result) {   
    echo "<script> alert('添加成功！') </script>";
    header("location:list_post.php");
  }
  else {
     echo "<script> alert('添加失败！') </script>";
  }

  $db->close();
?>
</body>
</html>
