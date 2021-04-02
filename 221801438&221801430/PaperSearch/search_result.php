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

            <div id="left" style="position:fixed;">
                <img class="head" src="image/head.png" alt="alt"/>

                <div class="icons">
                    <div class="this_" onclick="change1()">
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

            <div class="header" style="position:fixed; z-index:5;">
                <img src="image/logo.png" alt="alt"/>
                <a class="link" id="link1" href="#" target="_blank">首页</a>
                <a class="link" id="link2" href="https://www.cnki.net/" target="_blank">中国知网</a>
                <a class="link" id="link3" href="https://www.wanfangdata.com.cn/index.html" target="_blank">万方</a>
                <a class="link" id="link4" href="http://www.gov.cn/shuju/index.htm" target="_blank">国家数据</a>
            </div>

            <div class="search">
                <form name="search_form" action="search_result.php" method="get">
                    <input type="text" name="search_key" id="search_key"/>
                    <br/>
                    <input type="submit" name="bt_sure" id="submit_icon" value=""/>
                    <br/>
                </form>
            </div>

            <div class="results">
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

                    //查询数据 session传上一页输入框的值
                    @session_start();
                    $in = isset($_SESSION["input"]) ? $_SESSION["input"] : '';
                    
                    //有传来session的值
                    if ($in != "" ) {
                        $search_key = $_SESSION["input"];
                        unset($_SESSION["input"]);
                    }
                    else {
                        $search_key = isset($_GET["search_key"]) ? $_GET["search_key"] : "";
                    }
                   
                    //查找
                    $sql = "SELECT * FROM paper where post_title like '%".$search_key."%' or keywords like '%".$search_key."%'";
                    $result = $conn->query($sql);
                    echo "<script> document.getElementById('search_key').value = '".$search_key."';</script>";
                    //显示
                    echo '<P id="nums">为您查找到相关论文约'.$result->num_rows.'篇</P>';
                    if ($result->num_rows != 0) {
                       // 输出数据
                        while($row = $result->fetch_assoc()) {
                            echo '<div class="result_" id="result1">'.
                            '<textarea  rows="1" cols="100" class="paper_title" id="one" readonly="readonly">'.$row["post_title"].'</textarea>'.
                            '<p class="source" id="source_one">'.$row["meeting_date"].' ('.$row["release_date"].')</p>'.
                            '<textarea rows="3" cols="100" class="summary" id="summary_one" readonly="readonly">'.$row["post_content"].'</textarea>'.
                            '<a href='.$row["link"].' id="paper_link" target="_blank">阅读全文</a>'.
                            '<textarea rows="1" cols="100" class="keywords" id="keyword_one" readonly="readonly">'.$row["keywords"].'</textarea>'.
                            '<a href="search_result.php?title='.$row["post_title"].'" class="in_bt_one" id="bt_in_one" action="search_result.php"  onclick="insert_user()">导入</a>'.
                        '</div>';
                        }
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
                    //导入当前选择文章
                    $title = isset($_GET['title'])?$_GET['title']:'';
                    $sql = "select * from paper where post_title = '".$title."'";
                    $result = $conn->query($sql);
                    $row = $result->fetch_assoc();
                    $sql1 = "select * from paper_user where post_title = '".$title."'";
                    $result1 = $conn->query($sql1);
                    if (mysqli_num_rows($result1) == 0){
                        $sql2 = "insert into paper_user values ('".$row['post_title']."','".$row['post_content']."','".$row['release_date']."','".$row['keywords']."','".$row['release_date']."','".$row['link']."')";
                        $result2 = $conn->query($sql2);
                    }
                    
                    
                    $conn->close();
                ?>
            </div>
        </div>
    </body>

    <script>
        function change1() {
        //防止表单跳转
        event.returnValue = false;
        window.location.href = "sinplesearch.php";
        }
        function change2() {
            //防止表单跳转
            event.returnValue = false;
            window.location.href = "paper_list.php";
        }
        function change3() {
            //防止表单跳转
            event.returnValue = false;
            window.location.href = "data_analyse.html";
        }

        function insert_user(){
            alert('导入成功!');
        }

    </script>


</html>