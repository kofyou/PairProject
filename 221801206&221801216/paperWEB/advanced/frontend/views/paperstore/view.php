<?php

use yii\helpers\Html;
use yii\widgets\DetailView;

/* @var $this yii\web\View */
/* @var $model common\models\Paperstore */

$this->title = $model->storeID;
$this->params['breadcrumbs'][] = ['label' => 'Paperstores', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="paperstore-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Update', ['update', 'id' => $model->storeID], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('Delete', ['delete', 'id' => $model->storeID], [
            'class' => 'btn btn-danger',
            'data' => [
                'confirm' => 'Are you sure you want to delete this item?',
                'method' => 'post',
            ],
        ]) ?>
    </p>

    <?= DetailView::widget([
        'model' => $model,
        'attributes' => [
            'storeID',
            'displayTitle:ntext',
            'abstract:ntext',
            'year:ntext',
            'pubdate:ntext',
            'link:ntext',
            'keyword:ntext',
        ],
    ]) ?>

</div>
