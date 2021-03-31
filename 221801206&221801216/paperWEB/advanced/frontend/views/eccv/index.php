<?php

use yii\helpers\Html;
use yii\grid\GridView;
use yii\db\Connection;
use yii\widgets\ListView;

/* @var $this yii\web\View */
/* @var $searchModel common\models\EccvSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Eccv热词统计';
$this->params['breadcrumbs'][] = $this->title;
?> 

<div class="container">


    <div class="row">
       <?= GridView::widget([
           'dataProvider' => $dataProvider,
           'columns' => [
               'wordname',
               'wordcount',
    ]]); 
    ?> 
    </div>
 
</div>