<?php
use yii\helpers\Html;
use common\models\Papersearchlist;

?>

<div class="paper">
	<div class="test">
        <div class="title" >
        <br>
        <span  style="font-weight:bold" aria-hidden="true"></span>
        <em>

        <p class="glyphicon glyphicon-time" aria-hidden="true"></p><em><?= '发布时间：'.$model->pubdate."&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";?></em>
        <p><?= '论文名称：'.Html::encode($model->displayTitle);?></p>
        <?php                 
                $_id=$model->storeID;
                $_title=$model->displayTitle;
                $_abstract=$model->abstract;
                $_y=$model->year;
                $_pub=$model->pubdate;
                $_link=$model->link;
                $_key=$model->keyword;

          /*  echo    <button id="handled-button">Click Me</button>
                <script>
                $('#handled-button').click(function() {
                $.get('/myscript.php');
                });
                </script
                */
            //    echo '<a href="/paperWEB/advanced/common/addpaper.php?_id='.$_id.'">导入到论文列表</a>';
                        
               //addpaper_store($_id,$_title,$_abstract,$_y,$_pub,$_link,$_key),
//'&_title='.$_title.'&_abstract='.$_abstract.'&_y='.$_y.'&_pub='.$_pub.'&_link='.$_link.'&_key='.$_key.
      ?>   
        <?
        echo $_id;
        ?>
        <br>
        </em>	
	<hr>
        </div>
	</div>
	
</div>