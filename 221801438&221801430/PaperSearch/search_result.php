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
                <form name="search_form" action="paper_list.php" method="get">
                    <input type="text" name="search_key" id="search_key"/>
                    <br/>
                    <input type="submit" name="bt_sure" id="submit_icon" value=""/>
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
                    $conn = new mysqli($db_host, $db_username, $db_password, $db_database);
                    if (mysqli_connect_errno()) {
                       echo '错误: 无法连接到数据库. 请稍后再次重试.';
                       exit;
                    }
                    $conn->query("SET NAMES utf8");
                    //查询数据
                    $search_key = isset($_GET["search_key"]) ? $_GET["search_key"] : '';
                    $sql = "select * from paper where post_title like '%".$search_key."%'";
                    $result = $conn->query($sql);
                    //显示
                    if ($result->num_rows > 0) {
                        // 输出数据
                        while($row = $result->fetch_assoc()) {
                            echo '<div class="result_" id="result1">'.
                            '<img src="image/md-close.svg" class="icon_close" id="icon_close_one" alt="alt"/>'.
                            '<p class="paper_title" id="one">'.$row["post_title"].'</p>'.
                            '<p class="source" id="source_one">'.$row["meeting_date"].' ('.$row["release_date"].')</p>'.
                            '<textarea rows="3" cols="100" class="summary" id="summary_one" readonly="readonly">'.$row["post_content"].'</textarea>'.
                            '<a href='.$row["link"].' id="paper_link" target="_blank">阅读全文</a>'.
                            '<p class="keywords" id="keyword_one">'.$row["keywords"].'</p>'.
                            '<button class="in_bt_one" id="bt_in_one">导入</button>'.
                        '</div>';
                        }
                    } else {
                        echo '<h2>请输入标题查询！</h2>';
                    }
                    $conn->close();

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