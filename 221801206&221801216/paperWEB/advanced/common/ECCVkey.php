<?php
use yii\db\Connection;
function get_ek($filepath,$tname){

    if (is_dir ( $filepath )) //判断是不是目录
	{
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
                    $jsonfile = $filepath."/".$filename;                 
                    $json_data=file_get_contents($jsonfile);
                    $json_d=json_decode($json_data,true);

					$keyarr = $json_d['关键词']; 

					  //import keys to keywords table
					  $length=count($keyarr);
					  for($x=0;$x<$length;$x++)
					  {   $kstr='select * from '.$tname.' where wordname=\''.$keyarr[$x].'\'';
						  $k_exist=Yii::$app->db->createCommand($kstr)->queryAll();
						  if($k_exist!=NULL)
						  {
							  update_ekey($tname,$keyarr[$x],1);
						  }
						  else
						  {
							add_ekey($tname,$keyarr[$x]);
						  }
						}
                }
			}
			closedir ( $dirhandle );
	}
}

?>