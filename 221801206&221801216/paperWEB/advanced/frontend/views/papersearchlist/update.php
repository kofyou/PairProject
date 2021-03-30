<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model common\models\Papersearchlist */

$this->title = 'Update Papersearchlist: ' . $model->id;
$this->params['breadcrumbs'][] = ['label' => 'Papersearchlists', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id, 'url' => ['view', 'id' => $model->id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="papersearchlist-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
