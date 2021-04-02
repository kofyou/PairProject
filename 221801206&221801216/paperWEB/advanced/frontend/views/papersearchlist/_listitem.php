<?php
use yii\helpers\Html;
use common\models\Papersearchlist;
use common\models\Paperstore;
?>

<div class="paper">
	<div class="test">
        <div class="title" >
        <br>
        <span  style="font-weight:bold" aria-hidden="true"></span>
        <em>

        <p class="glyphicon glyphicon-time" aria-hidden="true"></p><em><?= '发布时间：'.$model->pubdate."&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";?></em>
        <p><?= '论文名称：'.Html::encode($model->displayTitle)?></p>    

        <?=Html::a('导入到论文列表',['/paperstore/addpaper','_id'=>$model->storeID],['onclick'=>'return confirm("是否确认导入到列表？")'])?>
   

        <br>
        </em>	
	<hr>
        </div>
	</div>
	
</div>