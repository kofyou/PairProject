<?php   
header("Content-type: application/json");
//跨域
 // $a = $_POST['a'];
 $raw = file_get_contents("php://input"); //获取POST数据
 //$raw = json_decode($HTTP_RAW_POST_DATA);
 
 //echo "postData=".$raw."\n";//测试post传入数据
    $mysqli = mysqli_connect('localhost:3306','root','','paper') or die("连接数据库失败");
    $paper = $mysqli->query("delete from papers where abstract = '%".$raw."%'");       
    if($paper)
    {
        echo "111";
    }
?>