<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Result</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" type="text/css" href="searchresult.css">

</head>
    <body>

        <div class="content">

            <div id="left">
                <img class="head" src="image/head.png" alt="alt"/>

                <div class="icons">
                    <div class="this_">
                        <img id="this_icon" src="image/mb-search.svg" alt="alt"/>
                        <p id="this_p">论文查询</p>
                    </div>
                    <div class="icon" id="icon2" onclick="change2()">
                        <img src="image/md-dehaze.svg" alt="alt"/>
                        <p>列表</p>
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

            <div class="header">
                <img src="image/logo.png"/>
                <a class="link" id="link1" href="#" target="_blank">首页</a>
                <a class="link" id="link2" href="https://www.cnki.net/" target="_blank">中国知网</a>
                <a class="link" id="link3" href="https://www.wanfangdata.com.cn/index.html" target="_blank">万方</a>
                <a class="link" id="link4" href="http://www.gov.cn/shuju/index.htm" target="_blank">国家数据</a>
            </div>

            <div class="search">
                <form name="search_form" action="" method="get">
                    <input type="text" name="search_key" id="search_key"/>
                    <br/>
                    <input type="submit" name="bt_sure" id="submit_icon" value=""/>  <!--设置点击事件-->
                    <br/>
                </form>
            </div>

            <div class="results">
                <P id="nums">为您查找到相关论文约15篇</P>
                <button id="bt_in">全部导入</button>
                <img src="image/icon_in.svg" id="icon_in"/>
                <?php

                    $db_host = "localhost";
                    $db_username = "root";
                    $db_password = "";
                    $db_database = "paperdb";

                    /*创建连接*/
                    function connect_db(){
                        $db = new mysqli($db_host, 	$db_username, $db_password, $db_database);
                        if (mysqli_connect_errno()) {
                            echo '错误: 无法连接到数据库. 请稍后再次重试.';
                            exit;
                        }
                        echo '打开数据库成功';
                        return $db;
                    }

                    /*查询论文*/
                    function query_db($paper_name){
                        $db = connect_db();
                        $sql = "select * from paper where paper_title like %".$_GET["search_key"].";";
                        $result = $db -> query ($sql);
                        //如果查询失败 输出错误
                        if($result == false){
                        //细节！对于多查询的时候，方便改错
                        echo "<br>执行失败的SQL语句是：".$sql;
                        echo"<br>失败原因是：".$conn->error;
                        exit();
                        }
                        //从结果集中返回数据
                        if($result->num_row>0){
                        while($row = $result->fetch_assoc()){

                        echo '<div class="result_">';
                        '<img src="image/md-close.svg" class="icon_close" id="icon_close_one"/>';
                        '<p class="paper_title" id="one">'.$row("post_title").'</p>';
                        '<p class="source" id="source_one">'.$row("meeting_date").'</p>';
                        '<p class="summary" id="summary_one">summary：'.$row("post_content").'</p>';
                        '<a href="'.$row("link").'" id="paper_link">阅读全文</a>';
                        '<p class="keywords" id="keyword_one">'.$row("keywords").'</p>';
                        '<button class="in_bt_one" id="bt_in_one">导入</button>';
                        '</div>';
                        }
                        }

                        //释放结果集和连接
                        $result -> free_result();
                        $db -> close();
                        }

                        ?>

            </div>

        </div>


    </body>

    <script>
        function change2() {
            //防止表单跳转
            event.returnValue = false;
            window.location.href = "paper_list.html";
        }
        function change3() {
            //防止表单跳转
            event.returnValue = false;
            window.location.href = "data_analyse.html";
        }


    </script>


</html>