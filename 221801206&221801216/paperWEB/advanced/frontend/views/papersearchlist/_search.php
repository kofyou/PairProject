<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model common\models\PapersearchlistSearch */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="papersearchlist-search">

    <?php $form = ActiveForm::begin([
        'action' => ['index'],
        'method' => 'get',
    ]); ?>

    <?= $form->field($model, 'id') ?>

    <?= $form->field($model, 'storeID') ?>

    <?= $form->field($model, 'displayTitle') ?>

    <?= $form->field($model, 'abstract') ?>

    <?= $form->field($model, 'year') ?>

    <?php // echo $form->field($model, 'pubdate') ?>

    <?php // echo $form->field($model, 'link') ?>

    <?php // echo $form->field($model, 'keyword') ?>

    <div class="form-group">
        <?= Html::submitButton('Search', ['class' => 'btn btn-primary']) ?>
        <?= Html::resetButton('Reset', ['class' => 'btn btn-default']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
