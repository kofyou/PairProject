<?php
  require('config.php');

  $db = new mysqli($db_host, 	$db_username, $db_password, $db_database);

  if (mysqli_connect_errno()) {
     echo '错误: 无法连接到数据库. 请稍后再次重试.';
     exit;
  }
    // 设置字符集
  $db->query("SET NAMES utf8");
    $query = "select * from user where username='$_POST[username]' and pwd='$_POST[pwd]'";
    $result = $db->query($query);
    $num_results = $result->num_rows;
    if($num_results){
       echo "<script> alert('登录成功！') </script>";
       session_start();
       $_SESSION['username']=$_POST["username"];
       header("location:list_post.php");
    }
    else { 
        echo "<script> alert('密码或用户名错误!') </script>";
    }
?>