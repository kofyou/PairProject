<?php
  require('config.php');

  $db = new mysqli($db_host, 	$db_username, $db_password, $db_database);

  if (mysqli_connect_errno()) {
     echo '错误: 无法连接到数据库. 请稍后再次重试.';
     exit;
  }
    // 设置字符集
  $db->query("SET NAMES utf8");

    $query = "update paper set post_title='$_POST[title]',post_content='$_POST[content]' where post_title='$_POST[oldtitle]'";
    $result = $db->query($query);

    if($result){
       echo "<script> alert('修改成功！') </script>";
       header("location:list_post.php");
    }
    else {
        echo "<script> alert('修改失败！') </script>";
    }
?>