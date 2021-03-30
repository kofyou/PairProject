<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model common\models\Paperstore */
?>
<div class="paperstore-addpaper">

    <div class="container">
        <div class="row">

           

                <ol class="breadcrumb">

                <li><a href="<?= Yii::$app->homeUrl;?>">首页</a></li>
                <li>导入结果</li>

                </ol>
                <br><br><hr>
                <?php if($added){?>
                    <h4>导入成功！是否前去论文列表查看?</h4>
                <?php  }else{?>
                    <h4>导入失败，论文列表中已有该篇论文。</h4> 
                <?php }?>
                <br><br><hr>
                <?=Html::a('回到论文广场',['/papersearchlist/index'])."&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"?>
                <?=Html::a('查看论文列表',['index'])?>
            

        </div>
    </div>
</div>