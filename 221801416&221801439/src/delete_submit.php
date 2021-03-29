<?php
  require('config.php');

  $db = new mysqli($db_host, 	$db_username, $db_password, $db_database);

  if (mysqli_connect_errno()) {
     echo '错误: 无法连接到数据库. 请稍后再次重试.';
     exit;
  }
    // 设置字符集
  $db->query("SET NAMES utf8");

    $query = "delete from paper where post_title='$_POST[title]'";
    $result = $db->query($query);

    if($result){
       echo "<script> alert('删除成功！') </script>";
       header("location:list_post.php");
    }
    else {
        echo "<script> alert('删除失败！') </script>";
    }
?>