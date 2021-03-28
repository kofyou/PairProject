<?php
function get_json($filepath){
    if (is_dir ( $filepath )) //判断是不是目录
	{
		$dirhandle = opendir ( $filepath );//打开文件夹的句柄
		if ($dirhandle) 
		{			//判断是不是有子文件或者文件夹
			while ( ($filename = readdir ( $dirhandle ))!= false ) 		
			{
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
					//打印文件
                    $jsonfile = $filepath."/".$filename;                 
                    $json_data=file_get_contents($jsonfile);
                    $json_d=json_decode($json_data,true);

                    echo $json_d['摘要'];
                    echo $json_d['会议和年份'];                    
					echo "<br/>";
				}
			}
			closedir ( $dirhandle );
		}
	}
	else
	{
		
		printFile($filepath . "/" . $filename);
		return;
	}
}
$resdir = "F:\jsonData\ECCV";
$result = get_json($resdir);
?>
