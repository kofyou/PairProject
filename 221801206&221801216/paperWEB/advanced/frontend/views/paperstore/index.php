<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel common\models\PaperSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = '论文列表';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="paperstore-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a('导入新论文到仓库', ['/papersearchlist/index'], ['class' => 'btn btn-success']) ?>
    </p>
    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'options' => [
            'style'=>'overflow: auto; word-wrap: normal;'
        ],
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'storeID',
            'displayTitle:ntext',
        //    'abstract:ntext',
            'year:ntext',
         //   'pubdate:ntext',
            'keyword:ntext',
            // 'link:ntext',
            ['class' => 'yii\grid\ActionColumn','header'=>'Operation','template' => '{view}  {delete}',

        ], 
    ]]); 
    ?> 
    
</div>
