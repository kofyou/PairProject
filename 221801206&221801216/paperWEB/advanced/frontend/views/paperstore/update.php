<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model common\models\Paperstore */

$this->title = 'Update Paperstore: ' . $model->storeID;
$this->params['breadcrumbs'][] = ['label' => 'Paperstores', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->storeID, 'url' => ['view', 'id' => $model->storeID]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="paperstore-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
