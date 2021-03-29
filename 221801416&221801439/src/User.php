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
    <title>登录</title>
    <link rel="stylesheet" href="stylesheet.css" type="text/css" />
  </head>
  <body>
  <div class="login">
  <h1 id="login">登录</h1>
  <form action="user_submit.php" method="post">
    <p><input type="text" name="username" id="name"  placeholder="请输入用户名"/></p>
    <p><input type="password" name="pwd"  id="password" placeholder="请输入密码"/></p> 
    <p><input type="submit" value="登录" id="btn"/></p>
  </form>
    <a href="list_post2.php" id="normal">游客登录</a>
    <br/>
    <br/>
    <p  id="ps">初始账号为：lhy  密码为：123</p>
  </div>
  </body>
</html>