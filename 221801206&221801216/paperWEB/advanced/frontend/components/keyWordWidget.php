<?php

namespace frontend\components;

use yii\base\Widget;
use yii\helpers\Html;
use common\models\Keywords;
use Yii;

class keyWordWidget extends Widget
{
	
	public function init()
	{
		parent::init();
	}
	
	public function run()
	{
		$keyString='';
		$keys = Keywords::find()->all();
		foreach ($keys as $key)
		{
			$url = Yii::$app->urlManager->createUrl(['papersearchlist/index','PapersearchlistSearch[keyword]'=>$key->keywordname]);
			$keyString .= '<a href="'.$url.'">'
			.' <h2 style="margin:10px 0;panding:0;"><span class="label label-info" style="background:#00e0e0;'
			.'display:inline-block;width:100%;">'
			.$key->keywordname.'</span></h2'.'></a>';
			//echo $typeString;
		}
		return $keyString;
		
	}	
}
?>