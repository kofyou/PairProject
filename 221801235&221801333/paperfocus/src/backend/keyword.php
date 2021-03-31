<?php   
      class login{
		//形成关键词表 
        function isExist() { 
			$mysqli = mysqli_connect('localhost:3306','root','','paper') or die("连接数据库失败");
			$user = $mysqli->query("select * from paper_ECCV");
			$num_results = $user->num_rows; 
			for ($i=0; $i <$num_results; $i++)
			{
				$row = $user->fetch_assoc();
				$name=$row["name"];
				$releaseTime=$row["releaseTime"];
				$matches = explode(';', $row["keyword"]);
				$matches= array_filter($matches);
				foreach($matches as $list=>$things){
					$mysqli->query("insert into keyword values('".$things."','".$name."','1','".$releaseTime."')");                
				}
			}        
		}
        //热门领域
        function statistics(){
          $mysqli = mysqli_connect('localhost:3306','root','','paper') or die("连接数据库失败");
          $user = $mysqli->query("select keyword,sum(num) as total from keyword group by keyword order by total DESC limit 10");
          $num_results = $user->num_rows; 
            for ($i=0; $i <$num_results; $i++)
            {
                $row = $user->fetch_assoc();
                //echo $row["keyword"].$row['total']."</br>";
				echo $row["keyword"]."*".$row['total'].'*';
            }
        }
        //热门领域的变化趋势
        function trend(){
          //获得十大热门领域
          $mysqli = mysqli_connect('localhost:3306','root','','paper') or die("连接数据库失败");
          //$user = $mysqli->query("select keyword,sum(num) as total from keyword group by keyword order by total DESC limit 10");
          $num_results = $user->num_rows; 
          $arr=array();
          for ($i=0; $i <$num_results; $i++)
          {
              $row = $user->fetch_assoc();
              array_push($arr,$row["keyword"]);
          }
		  //$things是关键词，
          foreach($arr as $list=>$things){  
            $year=array("ECCV 2016","ECCV 2018","ECCV 2019","ECCV 2020");                
            $a = $mysqli->query("select * from keyword where MeetingAndYear = '".$year[0]."' and keyword = '".$things."'");
            echo $a->num_rows;//16的论文数
            $b = $mysqli->query("select * from keyword where MeetingAndYear = '".$year[1]."' and keyword = '".$things."'");
            echo $b->num_rows; //18论文数
          }
        }
      }       
      $login=new login; 
	  $login->statistics();
	  //$login->trend();
    ?>