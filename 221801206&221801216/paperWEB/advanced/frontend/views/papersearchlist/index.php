<?php

use yii\helpers\Html;
use yii\grid\GridView;
use yii\widgets\ListView;
use yii\caching\DbDependency;
use common\models\Papersearchlist;
use common\models\PapersearchlistSearch;
/* @var $this yii\web\View */
/* @var $searchModel common\models\PapersearchlistSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

?>
<div class="container">

    <div class="row">

        <div class="col-md-9">
    
            <ol class="breadcrumb">

            <li><a href="<?= Yii::$app->homeUrl;?>">首页</a></li>
            <li>论文广场</li>
    
            </ol>
    
            <?= ListView::widget([
                    'id'=>'papersearchlist',
                    'dataProvider'=>$dataProvider,
                    'itemView'=>'_listitem',//子视图,显示一篇文章的标题等内容.
                    'layout'=>'{items} {pager}',
                    'pager'=>[
                          'maxButtonCount'=>10,
                          'nextPageLabel'=>Yii::t('app','下一页'),
                          'prevPageLabel'=>Yii::t('app','上一页'),
                    ],
            ])?>
    
        </div>

    
        <div class="col-md-3">
            <div class="searchbox">
                <ul class="list-group">
                <li class="list-group-item">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span> 查找文章(
                    <?= Papersearchlist::find()->count();?>
                    )
                </li>
                <li class="list-group-item">				  
                    <form class="form-inline" action="index.php?r=papersearchlist/index" id="w0" method="get">
                        <div class="form-group">
                            <input type="text" class="form-control" name="PapersearchlistSearch[displayTitle]" id="w0input" placeholder="按标题">
                        </div>
                      <button type="submit" class="btn btn-default">搜索</button>
                    </form>
                  </li>
                </ul>			
            </div>
        </div>
    
      
    
    
    </div>

   
</div>