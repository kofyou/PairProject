<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model common\models\Papersearchlist */

$this->title = 'Create Papersearchlist';
$this->params['breadcrumbs'][] = ['label' => 'Papersearchlists', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="papersearchlist-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
