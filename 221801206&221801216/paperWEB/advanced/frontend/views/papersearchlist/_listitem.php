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
        <?= Html::encode($model->displayTitle);?>
        <?= Html::a('导入到论文列表', ['create'],[
                'class' => 'btn btn btn-primary fanhui',
                'data' => [
                'confirm' => '是否确认导入？',
                'method' => 'post'
                ],
        ]) ?>
        </em>	
		<hr>
        </div>
	</div>
	
</div>