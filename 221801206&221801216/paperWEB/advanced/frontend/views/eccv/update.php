<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model common\models\Eccv */

$this->title = 'Update Eccv: ' . $model->id;
$this->params['breadcrumbs'][] = ['label' => 'Eccvs', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id, 'url' => ['view', 'id' => $model->id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="eccv-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
