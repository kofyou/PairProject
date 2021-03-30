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
            <div id="app">
                <el-dropdown>
                    <span class="el-dropdown-link" style="font-size: 18px; color: #757575; font-weight: bold">
                        排序方式<i class="el-icon-arrow-down el-icon--right"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>题名</el-dropdown-item>
                        <el-dropdown-item>发表时间</el-dropdown-item>
                        <el-dropdown-item>导入时间</el-dropdown-item>
                </el-dropdown>
            </div>
        </div>
        <img src="image/add.png" id="icon_add" alt="alt">
    </div>

    <div id="content">

        <form name="search_key" id="search_key">
            <input type="text" id="title" name="title" class="key" value="" placeholder="标题"/>
            <input type="text" id="author" name="author" class="key" value="" placeholder="会议"/>
            <input type="text" id="date" name="date" class="key" value="" placeholder="关键词"/>
            <input type="submit" id="ensure" name="ensure" value="在结果中检索" class="key"/>
        </form>

        <?php
            $db_host = "localhost";
            $db_username = "root";
            $db_password = "";
            $db_database = "paperdb";
            /*创建连接*/
            $conn = new mysqli($db_host, $db_username, $db_password, $db_database);
            if (mysqli_connect_errno()) {
               echo '错误: 无法连接到数据库. 请稍后再次重试.';
               exit;
            }
            $conn->query("SET NAMES utf8");
            //查询数据
            $sql = "SELECT * FROM paper_user";
            $result = $conn->query($sql);
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
            } else {
                echo "0 结果";
            }
            $conn->close();
        ?>

        <?php
            $db_host = "localhost";
            $db_username = "root";
            $db_password = "";
            $db_database = "paperdb";
            /*创建连接*/
            $conn = new mysqli($db_host, $db_username, $db_password, $db_database);
            if (mysqli_connect_errno()) {
                echo '错误: 无法连接到数据库. 请稍后再次重试.';
                exit;
            }
            $conn->query("SET NAMES utf8");
            //删除当前选择列表
            $title = isset($_GET['title'])?$_GET['title']:' ';
            $sql = "delete from paper_user where post_title='".$title."'";
            $result = $conn->query($sql);
            $conn->close();
        ?>
    </div>
</div>
</body>
<script>
    new Vue().$mount('#app')

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
</script>
</html>