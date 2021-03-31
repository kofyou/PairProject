<?php
use yii\db\Connection;
function get_json($filepath,$count){

    if (is_dir ( $filepath )) //判断是不是目录
	{
		$y="year";
		$dirhandle = opendir ( $filepath );//打开文件夹的句柄
			while ( ($filename = readdir ( $dirhandle ))!= false ) 		
			{
				$insertstr="";
				if ($filename == "." or $filename == "..")
				{
					//echo "目录为“.”或“..”"."<br/>";
					continue;
				}				
				//判断是否为目录，如果为目录递归调用函数，否则直接读取打印文件
				if(is_dir ($filepath . "/" . $filename ))
				{
					readFileRecursive($filepath . "/" . $filename);
				}
				else
				{
					$count+=1;
					
                    $jsonfile = $filepath."/".$filename;                 
                    $json_data=file_get_contents($jsonfile);
                    $json_d=json_decode($json_data,true);
					$tocpid="";
					$keyarr = $json_d['关键词']; 
					$topicid=implode(',',$keyarr);//implode keywords
					//import paper to papersearchlist table
					Yii::$app->db->createCommand()->insert('papersearchlist', [
						'id'=>$count,
						'storeID' => $count,
						'displayTitle' => $json_d['论文名称'],
						'abstract'=>$json_d['摘要'],
						'year'=>$json_d['会议和年份'],
						'pubdate'=>$json_d['发布时间'],
						'link'=>$json_d['原文链接'],
						'keyword'=>$topicid,
					  ])->execute();
					  //import keys to keywords table
					  $length=count($keyarr);
					  for($x=0;$x<$length;$x++)
					  {   $kstr='select * from keywords where keywordname=\''.$keyarr[$x].'\'';
						  $k_exist=Yii::$app->db->createCommand($kstr)->queryAll();
						  if($k_exist!=NULL)
						  {
							  update_key($keyarr[$x],1);
						  }
						  else
						  {
							add_key($keyarr[$x]);
						  }
						}
                }
			}
			closedir ( $dirhandle );
	}
}

?>