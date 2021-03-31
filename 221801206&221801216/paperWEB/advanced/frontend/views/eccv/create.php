<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model common\models\Eccv */

$this->title = 'Create Eccv';
$this->params['breadcrumbs'][] = ['label' => 'Eccvs', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="eccv-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
