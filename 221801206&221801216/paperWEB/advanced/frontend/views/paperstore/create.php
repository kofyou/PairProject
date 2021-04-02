<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model common\models\Paperstore */

$this->title = 'Create Paperstore';
$this->params['breadcrumbs'][] = ['label' => 'Paperstores', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="paperstore-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
