<?php   
header("Content-type: application/json");
//跨域
 // $a = $_POST['a'];
 $raw = file_get_contents("php://input"); //获取POST数据
 //解析Json
 $raw=json_decode($raw,true);
 //$raw = json_decode($HTTP_RAW_POST_DATA);
 
 //echo "postData=".$raw."\n";//测试post传入数据
    $mysqli = mysqli_connect('localhost:3306','root','','paper') or die("连接数据库失败");
	$paper = $mysqli->query("select * from paper_ECCV where abstract like  '%{$raw}%' or keyword like  '%{$raw}%' or releaseTime like  '%{$raw}%' or name like  '%{$raw}%' or MeetingAndYear like  '%{$raw}%'");
	$num_results = $paper->num_rows; 
    for ($i=0; $i <$num_results; $i++)
    {
        $row = $paper->fetch_assoc();
		$hh="***;%4";
		if(!$row["keyword"])
		echo $row["releaseTime"].$hh.$row["name"].$hh."no keyword".$hh.$row["MeetingAndYear"].$hh.$row["abstract"].$hh;
		else
		echo $row["releaseTime"].$hh.$row["name"].$hh.$row["keyword"].$hh.$row["MeetingAndYear"].$hh.$row["abstract"].$hh;	
    }
?>