<?php
 session_start();
 if(isset($_SESSION['username']))
 {
   header("location:list_post.php");
 }
 ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
  <head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <title>Simple Blog System—登录</title>
  </head>
  <body>

  <h1>登录</h1>
  <form action="user_submit.php" method="post">
    <p>用户名:</p>
    <p><input type="text" name="username" /></p>
    <p>密码:</p>
    <p><input type="password" name="pwd" /></p> 
    <p><input type="submit" value="登录" /></p>
  </form>
    <a href="list_post2.php">游客</a>
    <br/>
    <br/>
    <p>初始账号为：lhy  密码为：123</p>
  </body>
</html>