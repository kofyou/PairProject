<?php

/* @var $this \yii\web\View */
/* @var $content string */

use yii\helpers\Html;
use yii\bootstrap\Nav;
use yii\bootstrap\NavBar;
use yii\widgets\Breadcrumbs;
use frontend\assets\AppAsset;
use common\widgets\Alert;

AppAsset::register($this);
?>
<?php $this->beginPage() ?>
<!DOCTYPE html>
<html lang="<?= Yii::$app->language ?>">
<head>
    <meta charset="<?= Yii::$app->charset ?>">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <?= Html::csrfMetaTags() ?>
    <title><?= Html::encode($this->title) ?></title>
    <?php $this->head() ?>
<style type="text/css">
.navbar {
    background-color: #000000;
}
.wrap{
    background-size: 100%;
}
</style>

</head>
<body>
<?php $this->beginBody() ?>

<div class="wrap">
    <?php
    NavBar::begin([
     'brandLabel' => 'Paper WEB',  
     'brandOptions' => ['style'=>'color:white;font-size:23px'], 
     'brandUrl' => Yii::$app->homeUrl,
     'options' => [
        'class' => 'navbar-top navbar-fixed-top',
    ],
]);

echo Nav::widget([
    'items' => [
     ['label' => '论文广场', 'url' => ['/papersearchlist/index']],
     ['label' => '论文列表', 'url'=>['/paperstore/index']],
     ['label' => 'ECCV热词分析', 'options'=>['class'=>'ycn-banner'], 'items'=>[
        ['label' => '<i class="topics-tag"></i> ECCV2016', 'url' => ['/site/contact'],'encode' => false],
        ['label' => '<i class="topics-tag"></i> ECCV2018', 'url' => ['/site/contact'],'encode' => false],
        ['label' => '<i class="topics-tag"></i> ECCV2020', 'url' => ['/site/contact'],'encode' => false],
    ]
    ],
 ],
    'options' => ['class' => 'navbar-nav navbar-right'],
]);
NavBar::end();
    ?>

    <div class="container">
        <?= Breadcrumbs::widget([
            'links' => isset($this->params['breadcrumbs']) ? $this->params['breadcrumbs'] : [],
        ]) ?>
        <?= Alert::widget() ?>
        <?= $content ?>
    </div>
</div>

<footer class="footer">
    <div class="container">
        <p class="pull-left">&copy; Paper WEB <?= date('Y') ?></p>

        <p class="pull-right"><?= Yii::powered() ?>copyright@221801206&221801216</p>
    </div>
</footer>

<?php $this->endBody() ?>
</body>
</html>
<?php $this->endPage() ?>
