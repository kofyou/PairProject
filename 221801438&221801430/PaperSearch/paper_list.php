<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Paper List</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" type="text/css" href="paperlist.css">
    <script src="//unpkg.com/vue/dist/vue.js"></script>
    <script src="//unpkg.com/element-ui@2.15.1/lib/index.js"></script>

</head>
<body>

<div class="content">

    <div id="left" style="position:fixed">
        <img class="head" src="image/head.png" alt="alt"/>

        <div class="icons" style="top: 20%">
            <div class="icon" id="icon2" onclick="change1()">
                <img src="image/mb-search%202.svg" alt="搜索"/>
                <p>论文查询</p>
            </div>
            <div class="this_">
                <img src="image/md-dehaze%20Copy.svg" style="background-color: #356FC8; " id="this_icon" alt="alt">
                <p style="top: 0%; margin-top: 0px; margin-bottom: 90%" id="this_p">列表</p>
            </div>
            <div class="icon" id="icon3" onclick="change3()">
                <img src="image/antOutline-line-chart.svg" alt="alt"/>
                <p>数据分析</p>
            </div>
            <div class="icon" id="icon4">
                <img src="image/antOutline-setting.svg" alt="alt"/>
                <p>设置</p>
            </div>
        </div>
    </div>

    <div class="header" style="position:fixed; z-index:3">
        <img src="image/logo.png" alt="alt"/>
        <a class="link" id="link1" href="#" target="_blank">首页</a>
        <a class="link" id="link2" href="https://www.cnki.net/" target="_blank">中国知网</a>
        <a class="link" id="link3" href="https://www.wanfangdata.com.cn/index.html" target="_blank">万方</a>
        <a class="link" id="link4"href="http://www.gov.cn/shuju/index.htm" target="_blank">国家数据</a>
    </div>


    <div class="menu">
        <div class="font_menu">
            <form id="app" action="paper_list.php">
                <select name="sort">
                    <option onclick="orderbytitle()">排序方式</option>
                    <option value="selected1">标题</option>
                    <option value="selected2">会议时间</option>
                    <input id="confirm" type="submit" value="确认"></input>
                </select>
            </form>
        </div>
        <img src="image/add.png" id="icon_add" alt="alt" onclick="add()">
    </div>

    <div id="content">

        <form id="search_key">
            <input type="text" id="title" class="key" name="s_title" placeholder="标题"/>
            <input type="text" id="author" class="key" name="s_meeting" value="" placeholder="会议"/>
            <input type="text" id="date" class="key" name="s_key" value="" placeholder="关键词"/>
            <input type="submit" id="ensure" value="在关键词中搜索" class="key"/></input>
        </form>

        <?php
            //数据库信息
            $db_host = "localhost";
            $db_username = "root";
            $db_password = "";
            $db_database = "paperdb";

            //创建连接
            $conn = new mysqli($db_host, $db_username, $db_password, $db_database);
            if (mysqli_connect_errno()) {
               echo '错误: 无法连接到数据库. 请稍后再次重试.';
               exit;
            }
            //设置字符集
            $conn->query("SET NAMES utf8");

            //查看有没有排过序/搜索
            $select = isset($_GET['sort'])?$_GET['sort']:'';
            $search1 = isset($_GET['s_title'])?$_GET['s_title']:'';
            $search2 = isset($_GET['s_meeting'])?$_GET['s_meeting']:'';
            $search3 = isset($_GET['s_key'])?$_GET['s_key']:'';
            if($select == "selected1") {
                $sql = "SELECT * FROM paper_user order by post_title";
                $result = $conn->query($sql);
            }
            else if($select == "selected2") {
                $sql = "SELECT * FROM paper_user order by meeting_date";
                $result = $conn->query($sql);
            }
            else if($search1!= "" && $search2!= "" && $search3!= "") {
                $sql = "SELECT * FROM paper_user where post_title like '%".$search1."%' and meeting_date like '%".$search2."%' and keywords like '%".$search3."%'";
                $result = $conn->query($sql);
                echo "<script> document.getElementById('ensure').value = '查看全部文章';</script>";
            }
            else {
                $sql = "SELECT * FROM paper_user";
                $result = $conn->query($sql);
            }

            //显示
            if ($result->num_rows > 0) {
                // 输出数据
                while($row = $result->fetch_assoc()) {
                    echo '<div class="result_" id="result1">'.
                    '<p class="paper_title" id="one">'.$row["post_title"].'</p>'.
                    '<p class="source" id="source_one">'.$row["meeting_date"].' ('.$row["release_date"].')</p>'.
                    '<textarea rows="3" cols="100" class="summary" id="summary_one" readonly="readonly">'.$row["post_content"].'</textarea>'.
                    '<a href='.$row["link"].' id="paper_link" target="_blank">阅读全文</a>'.
                    '<p class="keywords" id="keyword_one">'.$row["keywords"].'</p>'.
                    '<a href="paper_list.php?title='.$row["post_title"].'" class="in_bt_one" id="bt_in_one" action="paper_list.php">删除</a>'.
                '</div>';
                }
            } 
            
            //关闭链接
            $conn->close();
        ?>

        <?php
            //数据库信息
            $db_host = "localhost";
            $db_username = "root";
            $db_password = "";
            $db_database = "paperdb";

            //创建连接
            $conn = new mysqli($db_host, $db_username, $db_password, $db_database);
            if (mysqli_connect_errno()) {
                echo '错误: 无法连接到数据库. 请稍后再次重试.';
                exit;
            }
            $conn->query("SET NAMES utf8");

            //删除当前选择列表
            $title = isset($_GET['title'])?$_GET['title']:'';
            $sql = "delete from paper_user where post_title='".$title."'";
            $result = $conn->query($sql);
            $conn->close();
        ?>
    </div>
</div>
</body>
<script>
    function change1() {
        //防止表单跳转
        event.returnValue = false;
        window.location.href = "sinplesearch.html";
    }
    function change3() {
        //防止表单跳转
        event.returnValue = false;
        window.location.href = "data_analyse.html";
    }
    function add() {
        //防止表单跳转
        event.returnValue = false;
        window.location.href = "sinplesearch.html";
    }
</script>
</html>